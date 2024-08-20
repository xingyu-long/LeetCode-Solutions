from typing import List

"""
219. Contains Duplicate II
---

Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:
Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
 
"""


class Solution:
    # time: O(n)
    # space: O(distinct of nums)
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        m = {}
        for idx, num in enumerate(nums):
            if num in m:
                if abs(idx - m[num]) <= k:
                    return True
            m[num] = idx
        return False
