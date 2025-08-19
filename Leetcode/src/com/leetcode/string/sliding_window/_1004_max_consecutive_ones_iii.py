from typing import List


class Solution:
    # time: O(n)
    # space: O(1)
    def longestOnes(self, nums: List[int], k: int) -> int:
        start = end = 0
        res = 0
        n = len(nums)
        count_zero = k
        while end < n:
            if nums[end] == 0:
                count_zero -= 1
            while count_zero < 0:
                if nums[start] == 0:
                    count_zero += 1
                start += 1
            res = max(res, end - start + 1)
            end += 1
        return res
