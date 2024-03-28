from typing import List

"""
128. Longest Consecutive Sequence
---

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.
---

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
"""


class Solution:
    # time: O(n) space: O(n)
    def longestConsecutive(self, nums: List[int]) -> int:
        hash_set = set(nums)
        res = 0
        for num in nums:
            if (num + 1) not in hash_set:
                curr = num
                count = 0
                # only go one direction
                while curr in hash_set:
                    count += 1
                    curr = curr - 1
                res = max(res, count)
        return res


class Solution2:
    def longestConsecutive(self, nums: List[int]) -> int:
        m = {}
        res = 0
        for num in nums:
            if num not in m:
                left = m.get(num - 1, 0)
                right = m.get(num + 1, 0)
                size = left + 1 + right
                m[num] = size
                res = max(res, size)
                # if we don't have neighbors, we will just update
                # m[num] with latest consecutive size
                # otherwise, neighbors update the size as well.
                m[num - left] = size
                m[num + right] = size

        return res
