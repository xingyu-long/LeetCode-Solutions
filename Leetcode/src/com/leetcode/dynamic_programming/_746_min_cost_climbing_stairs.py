from typing import List


class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        memo = {}

        def dfs(index, memo):
            if index >= len(cost):
                return 0
            if index in memo:
                return memo[index]
            step1 = cost[index] + dfs(index + 1, memo)
            step2 = cost[index] + dfs(index + 2, memo)

            memo[index] = min(step1, step2)
            return memo[index]

        return min(dfs(0, memo), dfs(1, memo))


class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        dp = [0] * n
        dp[0] = cost[0]
        dp[1] = cost[1]
        for i in range(2, n):
            dp[i] = cost[i] + min(dp[i - 1], dp[i - 2])

        # so from pos (n-1) / or pos (n - 2) to the end
        return min(dp[n - 1], dp[n - 2])
