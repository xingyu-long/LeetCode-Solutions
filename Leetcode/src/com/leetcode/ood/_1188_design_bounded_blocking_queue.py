from collections import deque
from threading import Condition, Lock


class BoundedBlockingQueue(object):
    def __init__(self, capacity: int):
        self.mutex = Lock()
        self.not_empty = Condition(self.mutex)
        self.not_full = Condition(self.mutex)
        self.queue = deque()
        self.capacity = capacity

    def enqueue(self, element: int) -> None:
        with self.not_full:
            while self._qsize() >= self.capacity:
                self.not_full.wait()
            self.queue.append(element)
            self.not_empty.notify()

    def dequeue(self) -> int:
        with self.not_empty:
            while self._qsize() == 0:
                self.not_empty.wait()
            item = self.queue.popleft()
            self.not_full.notify()
            return item

    def _qsize(self):
        return len(self.queue)

    def size(self) -> int:
        with self.mutex:
            return self._qsize()
