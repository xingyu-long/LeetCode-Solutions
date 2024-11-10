from typing import List


class Solution:
    def getMaximumXor(self, nums: List[int], maximumBit: int) -> List[int]:
        """
        mask will be the maxium XOR we can get
        prefix ^ k = mask <=> prefix ^ mask = k
        """
        mask = (1 << maximumBit) - 1
        prefix = 0
        n = len(nums)
        for num in nums:
            prefix ^= num
        res = []
        for i in range(n):
            res.append(prefix ^ mask)
            # exclude the last element
            prefix ^= nums[n - i - 1]
        return res
