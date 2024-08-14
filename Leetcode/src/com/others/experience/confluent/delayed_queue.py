import logging
import time

from datetime import datetime, timedelta
from functools import total_ordering, partial
from heapq import heappop, heappush
from threading import Condition, Lock, Thread


logger = logging.getLogger(__name__)


class Scheduler:

    @total_ordering
    class _Task:

        def __init__(self, name, fn, start) -> None:
            self.fn = fn
            self.name = name
            self.start = start
            self.cancelled = False

        def __le__(self, other):
            return self.start <= other.start

        @property
        def timeout(self):
            logger.info(f"{self}: {self.start}")
            return (self.start - datetime.now()).total_seconds()

        def cancel(self):
            self.cancelled = True
            logger.info(f"canceled {self}")

        def __repr__(self) -> str:
            return f"{self.name}"

    def __init__(self) -> None:
        self.task_number = 0
        self._cond = Condition(Lock())
        tasks = self._tasks = []

        def run():
            while True:
                with self._cond:
                    while True:
                        timeout = None
                        while tasks and tasks[0].cancelled:
                            heappop(tasks)
                        if tasks:
                            timeout = tasks[0].timeout
                            if timeout <= 0:
                                task = heappop(tasks)
                                break
                        self._cond.wait(timeout=timeout)
                logger.info(f"starting task {task}")
                tt = Thread(target=task.fn)
                tt.start()
                tt.join()

        self.t = Thread(target=run, name="Scheduler")
        self.t.start()

    def schedule(self, name, fn, start):
        task = self._Task(name, fn, start)
        logger.info(f"scheduling task {task}")
        with self._cond:
            heappush(self._tasks, task)
            self._cond.notify()
        logger.info(f"scheduled task {task}")
        return task


def main():
    logging.basicConfig(
        format="%(asctime)s %(levelname)-8s %(message)s",
        level=logging.INFO,
        datefmt="%Y-%m-%d %H:%M:%S",
    )
    # logging.basicConfig(level=logging.INFO, format="%(threadName)-10s: %(message)s")

    start = datetime.now()

    def task():
        logger.info(
            "running, elapsed: {}".format((datetime.now() - start).total_seconds())
        )

    s = Scheduler()
    s.schedule("task1", partial(task), start + timedelta(seconds=1))
    s.schedule("task2", partial(task), start + timedelta(seconds=2))
    s.schedule("task3", partial(task), start + timedelta(seconds=3))
    # note that task-4 precedes task-3, but is registered after task-3
    s.schedule("task4", partial(task), start + timedelta(seconds=2.5))
    time.sleep(5)
    now = datetime.now()
    s.schedule("task5", partial(task), now + timedelta(seconds=5))
    s.schedule("task6", partial(task), now + timedelta(seconds=4))
    s.schedule("task7", partial(task), now + timedelta(seconds=3.5))


if __name__ == "__main__":
    main()
