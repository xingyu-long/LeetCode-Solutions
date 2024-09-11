from typing import List


class Solution:
    def maxSubArrayLen(self, nums: List[int], k: int) -> int:
        # use prefix sum
        total = 0
        res = 0
        m = {0: -1}
        for i, num in enumerate(nums):
            total += num
            if total - k in m:
                res = max(res, i - m[total - k])
            if total not in m:
                m[total] = i
        return res
