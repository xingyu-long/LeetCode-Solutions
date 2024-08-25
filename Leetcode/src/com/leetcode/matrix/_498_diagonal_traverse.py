from typing import List
from collections import defaultdict


class Solution:
    # time: O(m*n)
    # space: O(m*n)
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        m, n = len(mat), len(mat[0])
        res = []

        d = defaultdict(list)
        for i in range(m):
            for j in range(n):
                d[i + j].append(mat[i][j])
        for i in range(m + n - 1):
            data = d[i][::-1] if i % 2 == 0 else d[i]
            for item in data:
                res.append(item)
        return res


class Solution2:
    # time: O(m*n)
    # space: O(m*n), we don't use additional memory
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        m, n = len(mat), len(mat[0])
        # 右上，或者左下
        dirs = [[-1, 1], [1, -1]]
        row = col = 0
        curr_dir = 0
        res = [0] * (m * n)
        for i in range(m * n):
            res[i] = mat[row][col]
            row += dirs[curr_dir][0]
            col += dirs[curr_dir][1]

            if row >= m:
                row = m - 1
                col += 2
                curr_dir = 1 - curr_dir
            if col >= n:
                col = n - 1
                row += 2
                curr_dir = 1 - curr_dir
            if row < 0:
                row = 0
                curr_dir = 1 - curr_dir
            if col < 0:
                col = 0
                curr_dir = 1 - curr_dir

        return res
