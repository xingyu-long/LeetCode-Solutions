from typing import List


class Solution:
    # time: O(target * n)
    # time: O(target * n)
    def canPartition(self, nums: List[int]) -> bool:
        total = sum(nums)
        if total % 2 != 0:
            return False
        n = len(nums)
        target = total // 2
        dp = [[False] * (target + 1) for _ in range(n + 1)]
        dp[0][0] = True
        for i in range(1, n + 1):
            dp[i][0] = True
            for j in range(1, target + 1):
                coin = nums[i - 1]
                not_use = dp[i - 1][j]
                use = dp[i - 1][j - coin] if j >= coin else False
                dp[i][j] = not_use or use

        return dp[n][target]
