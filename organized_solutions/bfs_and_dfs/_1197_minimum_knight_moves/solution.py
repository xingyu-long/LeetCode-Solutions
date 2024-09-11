from collections import deque


class Solution:
    # dirs 数组之前写错了
    def minKnightMoves(self, x: int, y: int) -> int:
        # BFS
        dirs = [(2, 1), (1, 2), (-1, 2), (-2, 1), (-2, -1), (-1, -2), (1, -2), (2, -1)]
        queue = deque()
        queue.append([0, 0])
        visited = set()
        visited.add((0, 0))
        level = 0
        while queue:
            size = len(queue)
            for _ in range(size):
                i, j = queue.popleft()
                if i == x and j == y:
                    return level
                for d in dirs:
                    new_i, new_j = i + d[0], j + d[1]
                    if (new_i, new_j) not in visited:
                        queue.append([new_i, new_j])
                        visited.add((new_i, new_j))
            level += 1
        return -1
