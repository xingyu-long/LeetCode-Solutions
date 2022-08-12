'''
Date: 08/11/2022 16:38:38
LastEditTime: 08/11/2022 16:43:01
Description: BFS
'''
from collections import deque
from typing import List


class Solution:
    def getFood(self, grid: List[List[str]]) -> int:
        def is_valid(grid, i, j):
            if i < 0 or i >= len(grid) or j < 0 or j >= len(grid[i]):
                return False
            if grid[i][j] == 'X':
                return False
            return True

        m, n = len(grid), len(grid[0])
        # [[False] * n] * m 不可以因为当我们处理某一列，其他列也会被更新
        # https://www.geeksforgeeks.org/python-using-2d-arrays-lists-the-right-way/
        visited = [[False for _ in range(n)] for _ in range(m)]
        queue = deque()
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '*':
                    queue.append((i, j))
                    visited[i][j] = True
                    break

        dirs = [[-1, 0], [0, -1], [1, 0], [0, 1]]
        step = 0
        while len(queue):
            size = len(queue)
            for _ in range(size):
                x, y = queue.popleft()
                if grid[x][y] == '#':
                    return step
                for move1, move2 in dirs:
                    new_x = x + move1
                    new_y = y + move2
                    if is_valid(grid, new_x, new_y) and not visited[new_x][new_y]:
                        queue.append((new_x, new_y))
                        visited[new_x][new_y] = True
            step += 1
        return -1
