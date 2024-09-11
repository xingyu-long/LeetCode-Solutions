from typing import List

"""
442. Find All Duplicates in an Array
---

Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.

You must write an algorithm that runs in O(n) time and uses only constant extra space.
---

Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]

Example 2:

Input: nums = [1,1,2]
Output: [1]

Example 3:

Input: nums = [1]
Output: []
"""


class Solution:
    def findDuplicates(self, nums: List[int]) -> List[int]:
        """
        like first missing positive
        all the integers of nums are in the range [1, n]

        idx:    0, 1, 2, 3, 4, 5
        target: 1, 2, 3, 4, 5, 6
        """

        def exch(i, j):
            nums[i], nums[j] = nums[j], nums[i]

        if not nums:
            return []
        for i in range(len(nums)):
            while (
                nums[i] - 1 >= 0
                and nums[i] - 1 < len(nums)
                and nums[i] != nums[nums[i] - 1]
            ):
                exch(i, nums[i] - 1)
        res = []
        for i in range(len(nums)):
            if nums[i] != i + 1:
                res.append(nums[i])
        return res


class Solution2:
    def findDuplicates(self, nums: List[int]) -> List[int]:
        if not nums:
            return []
        res = []
        for i in range(len(nums)):
            idx = abs(nums[i]) - 1
            if nums[idx] < 0:
                # for idx, the value should be idx + 1.
                res.append(idx + 1)
            else:
                # use negative to mark.
                nums[idx] = -nums[idx]
        return res
