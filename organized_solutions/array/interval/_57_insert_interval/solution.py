from typing import List


class Solution:
    def insert(
        self, intervals: List[List[int]], newInterval: List[int]
    ) -> List[List[int]]:
        res = []
        idx, n = 0, len(intervals)
        # 1) before newInterval
        start, end = newInterval
        while idx < n:
            if start > intervals[idx][1]:
                res.append(intervals[idx])
            else:
                break
            idx += 1

        # 2) intersection
        while idx < n:
            if end < intervals[idx][0]:
                break

            start = min(start, intervals[idx][0])
            end = max(end, intervals[idx][1])

            idx += 1
        res.append([start, end])

        # 3) add the rest
        while idx < n:
            res.append(intervals[idx])
            idx += 1

        return res
