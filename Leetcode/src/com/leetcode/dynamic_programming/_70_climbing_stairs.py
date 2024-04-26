class Solution:
    # without Memoization, it would be 2^n
    # time: O(n)
    # space: O(n)
    def climbStairs(self, n: int) -> int:

        def dfs(n, memo):
            if n == 0 or n == 1:
                return 1
            if n in memo:
                return memo[n]
            opt_1 = dfs(n - 1, memo)
            opt_2 = dfs(n - 2, memo)
            total = opt_1 + opt_2
            memo[n] = total
            return total

        return dfs(n, {})


class Solution2:
    # fibonacci sequence
    def climbStairs(self, n: int) -> int:
        dp = [0] * (n + 1)
        dp[0] = 1
        dp[1] = 1
        for i in range(2, n + 1):
            dp[i] = dp[i - 1] + dp[i - 2]
        return dp[n]
