import bisect
from math import inf
from operator import itemgetter


class CountIntervals:
    def __init__(self):
        self.curr_count = 0
        self.intervals = [[-inf, -inf], [inf, inf]]

    def add(self, left: int, right: int) -> None:
        left_index = bisect.bisect_left(self.intervals, left - 1, key=itemgetter(1))
        right_index = bisect.bisect_right(self.intervals, right + 1, key=itemgetter(0))

        left_val = min(left, self.intervals[left_index][0])
        right_val = max(right, self.intervals[right_index - 1][1])

        # old counts (and intervals)
        old_count = 0
        for i in range(left_index, right_index):
            old_count += self.intervals[i][1] - self.intervals[i][0] + 1

        self.curr_count += (right_val - left_val + 1) - old_count
        self.intervals[left_index:right_index] = [[left_val, right_val]]

    def count(self) -> int:
        return self.curr_count


# Your CountIntervals object will be instantiated and called as such:
# obj = CountIntervals()
# obj.add(left,right)
# param_2 = obj.count()
