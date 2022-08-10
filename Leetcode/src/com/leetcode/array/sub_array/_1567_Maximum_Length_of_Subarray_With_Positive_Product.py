'''
Date: 08/09/2022 15:13:24
LastEditTime: 08/09/2022 15:15:31
Description: Subarray, sequence DP(序列数组，依赖于前面的结果)
'''
from typing import List


class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        # only max length of positive prod
        if not nums:
            return 0
        n = len(nums)
        pos, neg = [0] * n, [0] * n
        if nums[0] > 0:
            pos[0] = 1
        elif nums[0] < 0:
            neg[0] = 1
        res = pos[0]
        for i in range(1, n):
            if nums[i] > 0:
                pos[i] = pos[i - 1] + 1
                neg[i] = (0 if neg[i - 1] == 0 else neg[i - 1] + 1)
            elif nums[i] < 0:
                pos[i] = (0 if neg[i - 1] == 0 else neg[i - 1] + 1)
                neg[i] = pos[i - 1] + 1
            res = max(res, pos[i])
        return res
