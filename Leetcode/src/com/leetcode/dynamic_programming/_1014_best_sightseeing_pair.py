from typing import List


class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        res = imax = 0
        for i, num in enumerate(values):
            res = max(res, imax + values[i] - i)
            imax = max(imax, values[i] + i)
        return res
