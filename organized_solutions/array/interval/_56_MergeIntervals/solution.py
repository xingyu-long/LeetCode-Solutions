from typing import List


class Solution:
    # time: O(nlogn)
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        arr = sorted(intervals, key=lambda x: (x[0], x[1]))
        res = [arr[0]]
        for start, end in arr:
            last_end = res[-1][1]
            if start <= last_end:
                # merge
                res[-1][1] = max(last_end, end)
            else:
                res.append([start, end])
        return res


class Solution2:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort(key=lambda x: x[0])
        res = []
        n = len(intervals)
        start, end = intervals[0]
        for i in range(1, n):
            i_start, i_end = intervals[i]
            if i_start > end:
                res.append([start, end])
                start, end = intervals[i]
            else:
                end = max(end, i_end)

        res.append([start, end])
        return res
