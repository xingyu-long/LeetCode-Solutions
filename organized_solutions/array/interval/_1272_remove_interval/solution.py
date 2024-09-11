from typing import List


class Solution:
    def removeInterval(
        self, intervals: List[List[int]], toBeRemoved: List[int]
    ) -> List[List[int]]:
        if not intervals:
            return []
        if not toBeRemoved:
            return intervals

        res = []
        n = len(intervals)
        for i in range(n):
            # no intersection
            if intervals[i][1] <= toBeRemoved[0] or toBeRemoved[1] <= intervals[i][0]:
                res.append(intervals[i])
            elif (
                toBeRemoved[0] <= intervals[i][0] and intervals[i][1] <= toBeRemoved[1]
            ):
                # the interval[i] block is included in toBeRemoved
                continue
            else:
                # any left from toBeRemoved
                if intervals[i][0] < toBeRemoved[0] < intervals[i][1]:
                    res.append([intervals[i][0], toBeRemoved[0]])
                if intervals[i][0] < toBeRemoved[1] < intervals[i][1]:
                    res.append([toBeRemoved[1], intervals[i][1]])

        return res
