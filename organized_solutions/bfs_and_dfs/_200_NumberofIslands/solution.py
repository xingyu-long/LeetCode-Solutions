from typing import List


class Solution:
    # time: O(m * n)
    # space: O(1)
    def numIslands(self, grid: List[List[str]]) -> int:
        res = 0
        m, n = len(grid), len(grid[0])

        def dfs(i, j):
            if not (0 <= i < m) or not (0 <= j < n):
                return
            if grid[i][j] != "1":
                return
            # mark as visited
            grid[i][j] = "#"
            dfs(i + 1, j)
            dfs(i - 1, j)
            dfs(i, j + 1)
            dfs(i, j - 1)

        for i in range(m):
            for j in range(n):
                if grid[i][j] == "1":
                    dfs(i, j)
                    res += 1

        return res
