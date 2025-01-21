from typing import List


class Solution:
    def gridGame(self, grid: List[List[int]]) -> int:
        n = len(grid[0])
        """
        x y y y
        x x x x -> x is the path for robot 1

        x x y y
        z x x x -> so the robot2 can get either bottom or top sums
        """
        prefix_row_1 = sum(grid[0])
        prefix_row_2 = 0
        res = float("inf")
        for i in range(n):
            # top
            prefix_row_1 -= grid[0][i]
            top = prefix_row_1
            # bottom
            bottom = prefix_row_2
            res = min(res, max(top, bottom))
            prefix_row_2 += grid[1][i]

        return res
