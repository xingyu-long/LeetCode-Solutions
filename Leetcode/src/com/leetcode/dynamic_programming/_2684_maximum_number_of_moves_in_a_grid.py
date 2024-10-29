from typing import List


class Solution:
    def maxMoves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        memo = {}

        def dfs(i, j, prev):
            if i < 0 or i >= m or j < 0 or j >= n:
                return 0
            if prev and grid[i][j] <= prev:
                return 0
            if (i, j) in memo:
                return memo[(i, j)]

            prev = grid[i][j]
            left = dfs(i - 1, j + 1, prev)
            right = dfs(i, j + 1, prev)
            down = dfs(i + 1, j + 1, prev)
            memo[(i, j)] = max(left, right, down) + 1
            return memo[(i, j)]

        res = 0
        for i in range(m):
            res = max(res, dfs(i, 0, None))

        return res - 1
