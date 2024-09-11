from typing import List


class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        m = {0: -1}
        # use 0 as -1 and 1 as 1
        # so we'd like to get max length of total = 0
        res = 0
        total = 0
        for i, num in enumerate(nums):
            total += num if num == 1 else -1
            if total in m:
                res = max(res, i - m[total])
            else:
                m[total] = i

        return res
