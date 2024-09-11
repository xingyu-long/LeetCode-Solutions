from collections import deque
from typing import List


class Solution:
    # time: O(m * n)
    def orangesRotting(self, grid: List[List[int]]) -> int:
        # BFS
        m, n = len(grid), len(grid[0])
        queue = deque()
        fresh = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 2:
                    queue.append((i, j))
                elif grid[i][j] == 1:
                    fresh += 1

        if fresh == 0:
            return 0
        res = -1
        if not queue:
            return res
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        while queue:
            size = len(queue)
            for _ in range(size):
                x, y = queue.popleft()
                for d in dirs:
                    i, j = x + d[0], y + d[1]
                    if not (0 <= i < m) or not (0 <= j < n):
                        continue
                    if grid[i][j] == 1:
                        # mark it
                        grid[i][j] = 2
                        fresh -= 1
                        queue.append((i, j))
            res += 1

        return res if fresh == 0 else -1
