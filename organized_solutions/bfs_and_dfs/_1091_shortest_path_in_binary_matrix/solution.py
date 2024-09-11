from collections import deque
from typing import List


class Solution:
    # time: O(m * n)
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        if grid[0][0] == 1:
            return -1
        m, n = len(grid), len(grid[0])
        dirs = [-1, 0, 1]
        queue = deque()
        queue.append((0, 0, 1))
        while queue:
            size = len(queue)
            for _ in range(size):
                x, y, count = queue.popleft()
                if x == m - 1 and y == n - 1:
                    return count
                for dx in dirs:
                    for dy in dirs:
                        if dx == 0 and dy == 0:
                            continue
                        new_x, new_y = dx + x, dy + y
                        # visited
                        if (
                            not 0 <= new_x < m
                            or not 0 <= new_y < n
                            or grid[new_x][new_y] == 1
                        ):
                            continue
                        grid[new_x][new_y] = 1
                        queue.append((new_x, new_y, count + 1))
        return -1
