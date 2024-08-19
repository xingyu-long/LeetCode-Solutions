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
