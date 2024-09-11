from sortedcontainers import SortedList


class MyCalendar:

    def __init__(self):
        self.arr = []

    # unsorted
    # time: O(n), space: O(n)
    def book(self, start: int, end: int) -> bool:
        for duration in self.arr:
            curr_start, curr_end = duration
            if start < curr_end and start >= curr_start:
                return False
            if end > curr_start and start <= curr_start:
                return False
        self.arr.append((start, end))
        return True


class MyCalendar:

    def __init__(self):
        self.data = SortedList(
            [(float("-inf"), float("-inf")), (float("inf"), float("inf"))]
        )

    # time: O(logN)
    def book(self, start: int, end: int) -> bool:
        idx = self.data.bisect_right((start, end))
        if start < self.data[idx - 1][1] or end > self.data[idx][0]:
            return False
        self.data.add((start, end))
        return True
