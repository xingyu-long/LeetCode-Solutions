from collections import deque


class MovingAverage:

    def __init__(self, size: int):
        self.queue = deque()
        self.limit = size
        self.total = 0

    def next(self, val: int) -> float:
        if len(self.queue) == self.limit:
            self.total -= self.queue.popleft()

        self.queue.append(val)
        self.total += val
        return self.total * 1.0 / len(self.queue)


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
