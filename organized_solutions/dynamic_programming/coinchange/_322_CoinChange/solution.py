from math import inf
from typing import List


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [0] * (amount + 1)
        for i in range(1, amount + 1):
            # from 1 to amount
            best = float(inf)
            for coin in coins:
                if i >= coin and dp[i - coin] != -1:
                    best = min(best, dp[i - coin] + 1)
            dp[i] = -1 if best == inf else best

        return dp[amount]
