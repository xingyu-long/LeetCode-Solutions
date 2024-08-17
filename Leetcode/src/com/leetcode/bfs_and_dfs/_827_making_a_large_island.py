from collections import defaultdict, deque
from typing import List


class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        # mark it and try it out
        m, n = len(grid), len(grid[0])
        has_zero = False
        res = 0
        dirs = [[-1, 0], [0, -1], [0, 1], [1, 0]]

        def dfs(grid, visited, i, j):
            if not 0 <= i < m or not 0 <= j < n:
                return 0
            if grid[i][j] == 0 or visited[i][j]:
                return 0
            visited[i][j] = True
            left = dfs(grid, visited, i - 1, j)
            right = dfs(grid, visited, i + 1, j)
            down = dfs(grid, visited, i, j - 1)
            up = dfs(grid, visited, i, j + 1)
            return 1 + left + right + up + down

        def bfs(grid, visited, i, j):
            queue = deque()
            queue.append((i, j))
            visited[i][j] = True
            total = 0
            while queue:
                total += 1
                curr_x, curr_y = queue.popleft()
                for d in dirs:
                    new_x, new_y = curr_x + d[0], curr_y + d[1]
                    if (
                        not 0 <= new_x < m
                        or not 0 <= new_y < n
                        or visited[new_x][new_y]
                        or grid[new_x][new_y] == 0
                    ):
                        continue
                    visited[new_x][new_y] = True
                    queue.append((new_x, new_y))
            return total

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    grid[i][j] = 1
                    visited = [[False] * n for _ in range(m)]
                    res = max(res, bfs(grid, visited, i, j))
                    if res == m * n:
                        return res
                    has_zero = True
                    # restore
                    grid[i][j] = 0

        return res if has_zero else m * n


class Solution:
    # solution 2: mark all the 1's group and then try it out from 0 and see what's nearby
    def largestIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dirs = [[0, 1], [1, 0], [-1, 0], [0, -1]]
        next_color = 2
        # color -> number of 1's
        dsu = defaultdict(int)

        def dfs(i, j, color):
            if i < 0 or i >= m or j < 0 or j >= n:
                return 0
            if grid[i][j] != 1:
                return 0
            grid[i][j] = color
            left = dfs(i - 1, j, color)
            right = dfs(i + 1, j, color)
            up = dfs(i, j - 1, color)
            down = dfs(i, j + 1, color)
            return 1 + left + right + up + down

        res = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    dsu[next_color] = dfs(i, j, next_color)
                    res = max(res, dsu[next_color])
                    next_color += 1

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    nei_colors = set()
                    for d in dirs:
                        new_x, new_y = i + d[0], j + d[1]
                        if new_x < 0 or new_x >= m or new_y < 0 or new_y >= n:
                            continue
                        # we don't need 0's neighbor
                        if grid[new_x][new_y] == 0:
                            continue
                        nei_colors.add(grid[new_x][new_y])
                    # turing current 0 to 1
                    # print(f"i={i}, j={j}, nei_colors={nei_colors}")
                    size = 1
                    for c in nei_colors:
                        size += dsu[c]
                    res = max(res, size)

        return res
