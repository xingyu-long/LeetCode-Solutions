import heapq
from typing import List


class Solution:
    def diagonalSort(self, mat: List[List[int]]) -> List[List[int]]:
        d = defaultdict(list)
        m, n = len(mat), len(mat[0])
        for i in range(m):
            for j in range(n):
                key = i - j
                heapq.heappush(d[key], mat[i][j])
        for i in range(m):
            for j in range(n):
                key = i - j
                mat[i][j] = heapq.heappop(d[key])
        return mat
