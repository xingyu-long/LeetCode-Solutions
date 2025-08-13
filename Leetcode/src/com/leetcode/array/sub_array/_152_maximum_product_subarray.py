from typing import List


class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [[0] * 2 for _ in range(n)]
        # 0: min, 1: max
        res = dp[0][0] = dp[0][1] = nums[0]
        for i in range(1, n):
            dp[i][0] = min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i], nums[i])
            dp[i][1] = max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i], nums[i])
            res = max(res, dp[i][0], dp[i][1])
        return res
