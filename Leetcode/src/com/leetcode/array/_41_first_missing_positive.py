from typing import List

"""
41. First Missing Positive
---

Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
---

Example 1:

Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.
"""


class Solution:
    def exch(self, nums, i, j):
        nums[i], nums[j] = nums[j], nums[i]

    def firstMissingPositive(self, nums: List[int]) -> int:
        """
        solution 1: sort and iterate the list

        solution 2:
            bucket sort? idx -> positive integer
            idx     0, 1, 2, 3, 4
            target  1, 2, 3, 4, 5
        """
        if not nums:
            return 1
        for i in range(len(nums)):
            while (
                nums[i] - 1 >= 0
                and nums[i] - 1 < len(nums)
                and nums[i] != nums[nums[i] - 1]
            ):
                self.exch(nums, i, nums[i] - 1)

        for i in range(len(nums)):
            if i + 1 != nums[i]:
                return i + 1
        return len(nums) + 1
