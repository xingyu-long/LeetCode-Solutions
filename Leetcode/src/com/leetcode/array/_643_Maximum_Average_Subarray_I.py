'''
Date: 12/15/2020 10:42:44
LastEditTime: 12/15/2020 10:42:56
Description: Sliding window
'''


class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        curr_sum = 0
        res = -inf
        for i in range(len(nums)):
            curr_sum += nums[i]
            if i >= k - 1:
                res = max(res, curr_sum / k)
                curr_sum -= nums[i - k + 1]
        return res
