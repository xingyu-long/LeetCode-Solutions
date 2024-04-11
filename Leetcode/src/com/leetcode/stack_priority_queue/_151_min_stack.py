from math import inf


class MinStack:

    def __init__(self):
        # two stacks
        self.stack = list()
        self.min_stack = list()

    def push(self, val: int) -> None:
        self.stack.append(val)
        if self.min_stack:
            min_val = self.min_stack[-1]
            if val <= min_val:
                self.min_stack.append(val)
        else:
            self.min_stack.append(val)

    def pop(self) -> None:
        val = self.stack.pop()
        if self.min_stack[-1] == val:
            self.min_stack.pop()

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.min_stack[-1]


class MinStack2:

    # use one stack to store current min first and then val.
    def __init__(self):
        self.stack = list()
        self.min = inf

    def push(self, val: int) -> None:
        if val <= self.min:
            self.stack.append(self.min)
            self.min = val
        self.stack.append(val)

    def pop(self) -> None:
        val = self.stack.pop()
        if val == self.min:
            self.min = self.stack.pop()

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.min
