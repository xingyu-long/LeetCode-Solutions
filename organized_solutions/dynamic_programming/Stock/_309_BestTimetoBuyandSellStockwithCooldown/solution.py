from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        """
                1   2   3   0    2
        buy.   -1  -1  -1   1    1
        sell    0  1   2    2    3
        cool    0  0   1    2    2

        (cool, nothing) -> buy
        (buy, nothing) -> sell
        (sell, nothing) -> cool
        """
        res = 0
        n = len(prices)
        dp = [[0] * 3 for _ in range(n)]
        dp[0][0] = -prices[0]
        for i in range(1, n):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][2] - prices[i])
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + prices[i])
            dp[i][2] = max(dp[i - 2][2], dp[i - 1][1])
            res = max(res, dp[i][1])
        return res
