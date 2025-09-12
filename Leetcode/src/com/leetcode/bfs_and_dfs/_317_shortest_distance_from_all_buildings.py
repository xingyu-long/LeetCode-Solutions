from collections import deque
from math import inf
from typing import List


class Solution:
    # time: O(M*N*M*N)
    # M: rows, N: cols
    # 1. starting from each building
    # 2. remember dist and reach for each i, j
    def shortestDistance(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        # the total distance of [i][j] to all buildings
        dist = [[0] * cols for _ in range(rows)]
        # the number of buildings they can reach for [i][j]
        reach = [[0] * cols for _ in range(rows)]

        def bfs(x, y):
            visited = set()
            queue = deque()
            queue.append((x, y, 0))
            visited.add((x, y))
            while queue:
                size = len(queue)
                curr_x, curr_y, cost = queue.popleft()
                for d in dirs:
                    new_x, new_y = curr_x + d[0], curr_y + d[1]
                    if not (0 <= new_x < rows and 0 <= new_y < cols):
                        continue
                    if (new_x, new_y) in visited:
                        continue
                    if grid[new_x][new_y] != 0:
                        continue

                    visited.add((new_x, new_y))
                    queue.append((new_x, new_y, cost + 1))
                    dist[new_x][new_y] += cost + 1
                    reach[new_x][new_y] += 1

        building_total = 0
        for i in range(rows):
            for j in range(cols):
                if grid[i][j] == 1:
                    building_total += 1
                    bfs(i, j)

        res = inf
        for i in range(rows):
            for j in range(cols):
                if (
                    grid[i][j] == 0
                    and reach[i][j] == building_total
                    and dist[i][j] < res
                ):
                    res = dist[i][j]

        return -1 if res == inf else res
