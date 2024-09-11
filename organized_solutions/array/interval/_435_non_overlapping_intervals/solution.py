from typing import List


class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], x[1]))
        end = intervals[0][1]
        res, n = 0, len(intervals)
        for i in range(1, n):
            if intervals[i][0] < end:
                res += 1
                # 选取小的作为end，这样我们可以删除最少的点
                end = min(end, intervals[i][1])
            else:
                end = intervals[i][1]
        return res
