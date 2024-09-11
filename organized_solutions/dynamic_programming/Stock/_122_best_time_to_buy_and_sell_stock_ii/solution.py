from typing import List


class Solution:
    # time: O(n)
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        dp = [[0] * 2 for _ in range(n)]
        # dp[i][0] -> buy
        # dp[i][1] -> sell
        dp[0][0] = -prices[0]
        res = 0
        for i in range(1, n):
            dp[i][0] = max(dp[i - 1][1] - prices[i], dp[i - 1][0])
            dp[i][1] = max(dp[i - 1][0] + prices[i], dp[i - 1][1])
            res = max(res, dp[i][1])
        return res
