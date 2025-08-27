from typing import List


class Solution:
    # Top-down DP
    # Time: O(M * target)
    # M: len(nums)
    def combinationSum4(self, nums: List[int], target: int) -> int:
        memo = dict()

        def dfs(target):
            if target == 0:
                return 1
            if target in memo:
                return memo[target]

            res = 0
            for num in nums:
                if target >= num:
                    res += dfs(target - num)

            memo[target] = res
            return res

        return dfs(target)

