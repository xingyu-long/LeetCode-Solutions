from threading import Barrier, Lock, Semaphore
from typing import Callable


class FooBar:
    def __init__(self, n):
        self.n = n
        self.foo_lock = Lock()
        self.bar_lock = Lock()
        self.bar_lock.acquire()

    def foo(self, printFoo: "Callable[[], None]") -> None:
        for i in range(self.n):
            self.foo_lock.acquire()

            # printFoo() outputs "foo". Do not change or remove this line.
            printFoo()

            self.bar_lock.release()

    def bar(self, printBar: "Callable[[], None]") -> None:
        for i in range(self.n):
            self.bar_lock.acquire()

            # printBar() outputs "bar". Do not change or remove this line.
            printBar()

            self.foo_lock.release()


class FooBar2:
    def __init__(self, n):
        self.n = n
        self.barrier = Barrier(2)

    def foo(self, printFoo: "Callable[[], None]") -> None:
        for i in range(self.n):
            # printFoo() outputs "foo". Do not change or remove this line.
            printFoo()

            self.barrier.wait()

    def bar(self, printBar: "Callable[[], None]") -> None:
        for i in range(self.n):
            self.barrier.wait()

            # printBar() outputs "bar". Do not change or remove this line.
            printBar()


class FooBar3:
    def __init__(self, n):
        self.n = n
        # semaphore with 0/1 is similar to lock
        self.foo_gate = Semaphore(1)
        self.bar_gate = Semaphore(0)

    def foo(self, printFoo: "Callable[[], None]") -> None:
        for i in range(self.n):
            self.foo_gate.acquire()

            # printFoo() outputs "foo". Do not change or remove this line.
            printFoo()

            self.bar_gate.release()

    def bar(self, printBar: "Callable[[], None]") -> None:
        for i in range(self.n):
            self.bar_gate.acquire()

            # printBar() outputs "bar". Do not change or remove this line.
            printBar()

            self.foo_gate.release()
