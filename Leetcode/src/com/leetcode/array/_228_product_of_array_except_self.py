from typing import List


"""
238. Product of Array Except Self
---

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.
---

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
---

tags: array, prefix
"""


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        """
        solution 1: brute force (two loops)
        solution 2:
            nums:      a0,           a1,         a2,       a3
            forward:   1,           a0         a0*a1      a0*a1*a2
            backward:  a1*a2*a3     a2*a3        a3            1
            target:   a1*a2*a3,    a0*a2*a3,  a0*a1*a3,  a0*a1*a2
        """
        n = len(nums)
        res = [0 for _ in range(n)]
        res[0] = 1
        backward = 1
        for i in range(1, n):
            res[i] = res[i - 1] * nums[i - 1]
        for i in range(n - 2, -1, -1):
            backward *= nums[i + 1]
            res[i] *= backward
        return res
