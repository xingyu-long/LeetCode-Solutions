from collections import deque
from typing import List


class Solution:
    # time: O(m * n)
    def wallsAndGates(self, rooms: List[List[int]]) -> None:
        """
        Do not return anything, modify rooms in-place instead.
        """
        m, n = len(rooms), len(rooms[0])
        queue = deque()

        for i in range(m):
            for j in range(n):
                if rooms[i][j] == 0:
                    queue.append((i, j))

        dirs = [[1, 0], [-1, 0], [0, 1], [0, -1]]
        while queue:
            size = len(queue)
            for _ in range(size):
                x, y = queue.popleft()
                for d in dirs:
                    i, j = x + d[0], y + d[1]
                    if not (0 <= i < m) or not (0 <= j < n):
                        continue
                    if rooms[i][j] == -1:
                        continue
                    if rooms[x][y] + 1 < rooms[i][j]:
                        rooms[i][j] = rooms[x][y] + 1
                        queue.append((i, j))
