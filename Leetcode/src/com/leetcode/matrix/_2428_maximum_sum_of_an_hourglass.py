from typing import List


class Solution:
    """
    Solution:
    只需要暴力解法即可

    time: O(m * n)
    """
    def maxSum(self, grid: List[List[int]]) -> int:
        dirs = [[0, 0], [0, 1], [0, 2], [1, 1], [2, 0], [2, 1], [2, 2]]
        m, n = len(grid), len(grid[0])
        res = 0
        for i in range(m - 2):
            for j in range(n - 2):
                curr = sum(grid[i + d[0]][j + d[1]] for d in dirs)
                res = max(res, curr)
        return res
