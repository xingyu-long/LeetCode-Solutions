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


# Your MyCalendar object will be instantiated and called as such:
# obj = MyCalendar()
# param_1 = obj.book(start,end)
