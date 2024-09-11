from math import inf
from typing import List


class Solution:
    # time: O(n^3)
    # space: O(n^2)
    def maxCoins(self, nums: List[int]) -> int:
        arr = [1] + nums + [1]
        memo = {}

        def dfs(i, j):
            # Q: how to describe the burst balloon?
            # A: just image we picked the last one to burst
            if i > j:
                return 0
            if (i, j) in memo:
                return memo[(i, j)]
            res = float(-inf)
            for x in range(i, j + 1):
                res = max(
                    res,
                    dfs(i, x - 1) + arr[i - 1] * arr[x] * arr[j + 1] + dfs(x + 1, j),
                )
            memo[(i, j)] = res
            return res

        return dfs(1, len(nums))
