from sortedcontainers import SortedDict


class MyCalendarTwo:

    def __init__(self):
        self.d = SortedDict()

    # sweep line
    def book(self, start: int, end: int) -> bool:
        self.d[start] = self.d.get(start, 0) + 1
        self.d[end] = self.d.get(end, 0) - 1
        count = 0
        for val in self.d.values():
            count += val
            if count > 2:
                # restore
                self.d[start] -= 1
                if self.d[start] == 0:
                    del self.d[start]
                self.d[end] += 1
                if self.d[end] == 0:
                    del self.d[end]
                return False
        return True
