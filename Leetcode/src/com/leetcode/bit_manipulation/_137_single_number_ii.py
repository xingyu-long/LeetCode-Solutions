from typing import List


class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        if not nums:
            return 0

        res = 0
        for i in range(32):
            total = 0
            for j in range(len(nums)):
                if (nums[j] >> i) & 1 == 1:
                    total += 1
            total %= 3
            if total != 0:
                res |= total << i
        return res if res < 2**31 else res - 2**32
