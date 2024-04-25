from typing import List


class Solution:
    # time: O(m * n)
    # space: O(m * n)
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        m, n = len(heights), len(heights[0])
        atlantic = [[False] * n for _ in range(m)]
        pacific = [[False] * n for _ in range(m)]
        res = []

        def dfs(arr, i, j, prev):
            if not (0 <= i < m) or not (0 <= j < n):
                return
            # has visited
            if arr[i][j]:
                return
            if prev and heights[i][j] < prev:
                return

            prev = heights[i][j]
            arr[i][j] = True
            dfs(arr, i, j - 1, prev)
            dfs(arr, i, j + 1, prev)
            dfs(arr, i - 1, j, prev)
            dfs(arr, i + 1, j, prev)

        # populate atlantic and pacific arr
        for i in range(m):
            dfs(atlantic, i, n - 1, None)
            dfs(pacific, i, 0, None)

        for j in range(n):
            dfs(atlantic, m - 1, j, None)
            dfs(pacific, 0, j, None)

        for i in range(m):
            for j in range(n):
                if atlantic[i][j] and pacific[i][j]:
                    res.append([i, j])
        return res
