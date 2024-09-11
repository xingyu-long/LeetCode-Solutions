from sortedcontainers import SortedDict


class MyCalendarThree:

    def __init__(self):
        self.d = SortedDict()

    def book(self, startTime: int, endTime: int) -> int:
        self.d[startTime] = self.d.get(startTime, 0) + 1
        self.d[endTime] = self.d.get(endTime, 0) - 1
        count = 0
        res = 0
        for val in self.d.values():
            count += val
            res = max(res, count)
        return res
