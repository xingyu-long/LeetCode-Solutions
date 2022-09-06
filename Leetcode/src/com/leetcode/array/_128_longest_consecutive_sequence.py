'''
Date: 09/05/2022 19:53:22
LastEditTime: 09/05/2022 19:53:22
Description: Set
'''
from typing import List


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
