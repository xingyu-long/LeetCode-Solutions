from collections import deque
from typing import List


class Solution:
    # 从每一个建筑物出发，对于每一个0的位置，我们记录能到建筑的个数reach以及到达各个建筑物的距离dist
    # time: O(M*N*M*N)
    def shortestDistance(self, grid: List[List[int]]) -> int:
        # bfs
        m, n = len(grid), len(grid[0])
        dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        reach = [[0] * n for _ in range(m)]
        dist = [[0] * n for _ in range(m)]

        def bfs(x, y):
            visited = set()
            queue = deque()
            queue.append((x, y))
            visited.add((x, y))
            level = 0
            while queue:
                level += 1
                size = len(queue)
                for _ in range(size):
                    curr_x, curr_y = queue.popleft()
                    for d in dirs:
                        new_x, new_y = curr_x + d[0], curr_y + d[1]
                        if new_x >= m or new_x < 0 or new_y >= n or new_y < 0:
                            continue
                        if (new_x, new_y) in visited:
                            continue
                        if grid[new_x][new_y] != 0:
                            continue
                        visited.add((new_x, new_y))
                        queue.append((new_x, new_y))
                        dist[new_x][new_y] += level
                        reach[new_x][new_y] += 1

        building_total = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    building_total += 1
                    bfs(i, j)

        res = float("inf")
        for i in range(m):
            for j in range(n):
                if (
                    grid[i][j] == 0
                    and reach[i][j] == building_total
                    and dist[i][j] < res
                ):
                    res = dist[i][j]
        return -1 if res == float("inf") else res
