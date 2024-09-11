from heapq import heappop, heappush


class MaxStack:

    def __init__(self):
        self.heap = []
        # use it to identify same element
        self.idx = 0
        self.stack = []
        # used it for lazy deletion
        self.removed = set()

    def push(self, x: int) -> None:
        heappush(self.heap, (-x, -self.idx))
        self.stack.append((x, self.idx))
        self.idx += 1

    def pop(self) -> int:
        while self.stack and self.stack[-1][1] in self.removed:
            self.stack.pop()
        num, idx = self.stack.pop()
        self.removed.add(idx)
        return num

    def top(self) -> int:
        while self.stack and self.stack[-1][1] in self.removed:
            self.stack.pop()
        return self.stack[-1][0]

    def peekMax(self) -> int:
        while self.heap and -self.heap[0][1] in self.removed:
            heappop(self.heap)
        return -self.heap[0][0]

    def popMax(self) -> int:
        while self.heap and -self.heap[0][1] in self.removed:
            heappop(self.heap)
        num, idx = heappop(self.heap)
        self.removed.add(-idx)
        return -num
