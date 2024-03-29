'''
Date: 11/17/2021 21:19:31
LastEditTime: 11/17/2021 21:19:31
Description: Array
'''
from typing import List


class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        # in-space solution
        for num in nums:
            idx = abs(num) - 1
            nums[idx] = -abs(nums[idx])
        return [i + 1 for i in range(len(nums)) if nums[i] > 0]
