from threading import Barrier, Semaphore
from typing import Callable


class H2O:

    def __init__(self):
        self.h = Semaphore(2)
        self.o = Semaphore(1)
        self.b = Barrier(3)

    def hydrogen(self, releaseHydrogen: "Callable[[], None]") -> None:
        with self.h:
            self.b.wait()
            releaseHydrogen()

    def oxygen(self, releaseOxygen: "Callable[[], None]") -> None:
        with self.o:
            self.b.wait()
            releaseOxygen()
