'''
Date: 04/11/2022 20:52:30
LastEditTime: 04/11/2022 20:55:52
Description: You need to fill out
'''


from typing import List


class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        if not grid:
            return None
        m, n = len(grid), len(grid[0])
        repeat = m * n
        k %= repeat
        for _ in range(k):
            new_matrix = [[0 for _ in range(n)] for _ in range(m)]
            print(grid)
            for i in range(m):
                for j in range(n):
                    if j + 1 < n:
                        new_matrix[i][j + 1] = grid[i][j]
                        print(f"here 1: {new_matrix}")
                    elif i + 1 < m and j == n - 1:
                        new_matrix[i + 1][0] = grid[i][n - 1]
                        print(f"here 2: {new_matrix}")
            new_matrix[m - 1][n - 1] = grid[0][0]
            grid = new_matrix
        return grid

if __name__ == '__main__':
    solution = Solution()
    grid = [[1,2,3], [4,5,6], [7,8,9]]
    solution.shiftGrid(grid, 1)
    