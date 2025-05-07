from typing import List


class Solution:
    def buildArray(self, nums: List[int]) -> List[int]:
        """
        a = q * b + r

        we want to use
            b = nums[nums[i]]
            r = nums[i]

        """
        n = q = len(nums)
        for i in range(n):
            r = nums[i]
            b = nums[nums[i]] % q
            nums[i] = q * b + r
        for i in range(n):
            # to get b
            nums[i] = nums[i] // q
        return nums
