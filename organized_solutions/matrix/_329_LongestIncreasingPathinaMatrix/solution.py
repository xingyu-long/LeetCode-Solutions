from typing import List


class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:

        m, n = len(matrix), len(matrix[0])
        memo = {}
        res = 0

        def dfs(i, j, prev):
            if i < 0 or i >= m or j < 0 or j >= n:
                return 0
            if prev != None and matrix[i][j] <= prev:
                return 0
            key = (i, j)

            if key in memo:
                return memo[key]

            prev = matrix[i][j]

            left = dfs(i, j - 1, prev)
            right = dfs(i, j + 1, prev)
            up = dfs(i - 1, j, prev)
            down = dfs(i + 1, j, prev)

            res = max(left, right, up, down) + 1
            memo[key] = res
            return res

        for i in range(m):
            for j in range(n):
                res = max(res, dfs(i, j, None))
        return res
