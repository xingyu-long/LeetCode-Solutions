from threading import Barrier, Lock
from typing import Callable


# Solution 1: use Barrier
class Foo:
    def __init__(self):
        self.first_barrier = Barrier(2)
        self.second_barrier = Barrier(2)

    def first(self, printFirst: "Callable[[], None]") -> None:
        # printFirst() outputs "first". Do not change or remove this line.
        printFirst()

        # STEP 1: thread would call printFirst() in any condition.
        self.first_barrier.wait()

    def second(self, printSecond: "Callable[[], None]") -> None:
        # STEP 2: thread-2 would wait until we called first_barrier.wait() in first(),
        # so that, first_barrier reached parties = 2.
        self.first_barrier.wait()

        # printSecond() outputs "second". Do not change or remove this line.
        printSecond()

        self.second_barrier.wait()

    def third(self, printThird: "Callable[[], None]") -> None:
        self.second_barrier.wait()

        # printThird() outputs "third". Do not change or remove this line.
        printThird()


# Solution 2: use Lock
class Foo2:
    def __init__(self):
        self.locks = (Lock(), Lock())
        self.locks[0].acquire()
        self.locks[1].acquire()

    def first(self, printFirst: "Callable[[], None]") -> None:
        # printFirst() outputs "first". Do not change or remove this line.
        printFirst()
        self.locks[0].release()

    def second(self, printSecond: "Callable[[], None]") -> None:
        with self.locks[0]:
            # printSecond() outputs "second". Do not change or remove this line.
            printSecond()
            self.locks[1].release()

    def third(self, printThird: "Callable[[], None]") -> None:
        with self.locks[1]:
            # printThird() outputs "third". Do not change or remove this line.
            printThird()
