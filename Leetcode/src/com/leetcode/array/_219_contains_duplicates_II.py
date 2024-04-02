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
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        m = {}
        for idx, num in enumerate(nums):
            if num not in m:
                m[num] = list()
            m[num].append(idx)
        for key in m.keys():
            for i in range(len(m[key])):
                if i > 0 and abs(m[key][i] - m[key][i - 1]) <= k:
                    return True
        return False


class Solution2:
    # time: O(n)
    # space: O(distinct of nums)
    # similar to Solution above, however, we don't need to record idx every time.
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        m = {}
        for idx, num in enumerate(nums):
            if num in m:
                if abs(idx - m[num]) <= k:
                    return True
            m[num] = idx
        return False
