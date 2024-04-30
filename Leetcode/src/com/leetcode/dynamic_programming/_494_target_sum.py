from typing import List


class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:

        def dfs(index, target, memo):
            if index == len(nums):
                return 1 if target == 0 else 0

            key = (index, target)
            if key in memo:
                return memo[key]

            res = 0
            res += dfs(index + 1, target - nums[index], memo)
            res += dfs(index + 1, target + nums[index], memo)

            memo[key] = res
            return memo[key]

        memo = {}
        return dfs(0, target, memo)
