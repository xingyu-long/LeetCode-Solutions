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
