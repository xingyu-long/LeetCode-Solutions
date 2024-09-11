from typing import List


class Solution:
    # number of islands的变形
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:

        m, n = len(grid1), len(grid1[0])

        def dfs(i, j, grid):
            if i < 0 or i >= m or j < 0 or j >= n:
                return
            if grid[i][j] == 0:
                return
            grid[i][j] = 0
            dfs(i, j + 1, grid)
            dfs(i, j - 1, grid)
            dfs(i + 1, j, grid)
            dfs(i - 1, j, grid)

        # 1. remove all non-sub islands
        for i in range(m):
            for j in range(n):
                # if grid1 didn't cover grid2
                if grid2[i][j] == 1 and grid1[i][j] == 0:
                    dfs(i, j, grid2)

        # 2. count the sub island again
        res = 0
        for i in range(m):
            for j in range(n):
                if grid2[i][j] == 1:
                    dfs(i, j, grid2)
                    res += 1
        return res
