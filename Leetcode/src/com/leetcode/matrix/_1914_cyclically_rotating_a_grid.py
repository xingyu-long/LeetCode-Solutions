from typing import List


class Solution:
    # time: O(M * N * K)
    # similar to 59. Spiral Matrix
    def rotateGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        row_start = col_start = 0
        row_end, col_end = len(grid) - 1, len(grid[0]) - 1
        while row_start < row_end and col_start < col_end:
            total_in_layer = (row_end - row_start) * 2 + (col_end - col_start) * 2
            local_k = k % total_in_layer
            for _ in range(local_k):
                temp = grid[row_start][col_start]
                for j in range(col_start, col_end):
                    grid[row_start][j] = grid[row_start][j + 1]

                for i in range(row_start, row_end):
                    grid[i][col_end] = grid[i + 1][col_end]

                for j in range(col_end, col_start, -1):
                    grid[row_end][j] = grid[row_end][j - 1]

                for i in range(row_end, row_start, -1):
                    grid[i][col_start] = grid[i - 1][col_start]

                grid[row_start + 1][col_start] = temp

            row_start += 1
            row_end -= 1
            col_start += 1
            col_end -= 1

        return grid
