from typing import List


class Solution:
    # time: O(m * n)
    # space: O(m * n)
    def change(self, amount: int, coins: List[int]) -> int:
        """
             0 1 2 3 4 5
        0    1 0 0 0 0 0
        1    1 1 1 1 1 1
        2    1 1 2 2 3 3
        5    1 1 2 2 3 4
        """
        m = len(coins)
        n = amount
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(m + 1):
            dp[i][0] = 1
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = dp[i - 1][j]
                # use other coin types
                if j >= coins[i - 1]:
                    dp[i][j] += dp[i][j - coins[i - 1]]
        return dp[m][n]
