from typing import List


class Solution:
    # time: O(nlog + n)
    def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
        if not intervals:
            return True
        # check if we have conflicted meetings
        n = len(intervals)
        intervals.sort(key=lambda x: x[0])
        _, end = intervals[0]
        for i in range(1, n):
            if intervals[i][0] < end:
                return False
            else:
                _, end = intervals[i]
        return True
