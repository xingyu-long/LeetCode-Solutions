from typing import List


class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        res = 0
        j_min = j_max = j_bad = -1
        for i, num in enumerate(nums):
            if not minK <= num <= maxK:
                j_bad = i
            if num == minK:
                j_min = i
            if num == maxK:
                j_max = i
            # min(j_min, j_max) - (j_bad + 1) + 1
            # -> min(j_min, j_max) - j_bad
            res += max(0, min(j_min, j_max) - j_bad)

        return res
