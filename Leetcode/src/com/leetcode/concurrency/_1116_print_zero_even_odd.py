from threading import Barrier, Lock
from typing import Callable


class ZeroEvenOdd:
    def __init__(self, n):
        self.n = n
        self.count = 0
        self.barriers = [Barrier(2), Barrier(2)]
        self.zero_lock = Lock()

    # printNumber(x) outputs "x", where x is an integer.
    def zero(self, printNumber: "Callable[[int], None]") -> None:
        for _ in range(self.n):
            self.zero_lock.acquire()
            printNumber(0)
            self.count += 1
            # choose one Barrier to hit, so that one of thread can
            # reach the barrier and unblock itself.
            self.barriers[self.count % 2].wait()

    def even(self, printNumber: "Callable[[int], None]") -> None:
        for _ in range(self.n // 2):
            self.barriers[0].wait()
            printNumber(self.count)
            self.zero_lock.release()

    def odd(self, printNumber: "Callable[[int], None]") -> None:
        for _ in range(self.n - self.n // 2):
            self.barriers[1].wait()
            printNumber(self.count)
            self.zero_lock.release()
