from typing import List


class Solution:
    def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
        if not intervals:
            return True

        intervals.sort(key=lambda x: x[0])
        n = len(intervals)
        for i in range(1, n):
            if intervals[i - 1][1] > intervals[i][0]:
                return False
        return True
