from typing import List


class Solution:
    # time: O(target * n)
    # space: O(target * n)
    # top-down dynamic programming with memoization
    def canPartition(self, nums: List[int]) -> bool:
        # either include or not into the sum
        total = sum(nums)
        if total % 2 != 0:
            return False
        target = total // 2

        n = len(nums)
        memo = {}

        def dfs(index, target):
            if target == 0:
                return True
            if index >= n or target < 0:
                return False

            key = (index, target)
            if key in memo:
                return memo[key]

            result = dfs(index + 1, target) or dfs(index + 1, target - nums[index])
            memo[key] = result
            return result

        return dfs(0, target)


class Solution2:
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
