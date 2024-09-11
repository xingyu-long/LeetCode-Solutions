from typing import List


class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        m, n = len(matrix), len(matrix[0])
        # flip it for each row
        for i in range(m):
            l, r = 0, n - 1
            while l < r:
                matrix[i][l], matrix[i][r] = matrix[i][r], matrix[i][l]
                l += 1
                r -= 1

        for i in range(m):
            for j in range(n - i):
                new_j = m - i - 1
                new_i = n - j - 1
                matrix[i][j], matrix[new_i][new_j] = matrix[new_i][new_j], matrix[i][j]
