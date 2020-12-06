'''
Date: 11/27/2020 11:48:56
LastEditTime: 11/27/2020 11:49:21
Description: 0/1 knapsack
'''


class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        if not nums:
            return False
        total = sum(nums)
        if total % 2 != 0:
            return False
        num = len(nums)
        target = total // 2
        dp = [[False for _ in range(target + 1)] for _ in range(num + 1)]
        dp[0][0] = True
        for i in range(1, num + 1):
            dp[i][0] = True

        for i in range(1, num + 1):
            for j in range(1, target + 1):
                dp[i][j] = dp[i - 1][j]
                if j >= nums[i - 1]:
                    dp[i][j] = dp[i][j] or dp[i - 1][j - nums[i - 1]]

        return dp[num][target]
