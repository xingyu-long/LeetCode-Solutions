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


class Solution2:
    def coinChange(self, coins: List[int], amount: int) -> int:
        memo = dict()

        def dfs(target):
            if target == 0:
                return 0
            if target < 0:
                return inf
            if target in memo:
                return memo[target]

            min_count = inf
            for coin in coins:
                next_iteration = dfs(target - coin)
                if next_iteration != inf:
                    min_count = min(min_count, 1 + next_iteration)

            memo[target] = min_count
            return min_count

        res = dfs(amount)
        return res if res != inf else -1
