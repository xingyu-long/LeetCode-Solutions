"""
This is GridMaster's API interface.
You should not implement it, or speculate about its implementation
"""

from collections import deque


class GridMaster(object):
    def canMove(self, direction: str) -> bool:
        pass

    def move(self, direction: str) -> bool:
        pass

    def isTarget(self) -> None:
        pass


class Solution(object):
    def findShortestPath(self, master: "GridMaster") -> int:
        # DFS + BFS
        dirs = {"U": [-1, 0], "D": [1, 0], "L": [0, -1], "R": [0, 1]}
        anti = {"U": "D", "D": "U", "L": "R", "R": "L"}
        is_valid = {}
        # record all paths and also if it's target
        is_valid[(0, 0)] = master.isTarget()

        def dfs(x, y):
            for d in dirs:
                d0, d1 = dirs[d]
                new_x, new_y = x + d0, y + d1
                if (new_x, new_y) not in is_valid and master.canMove(d):
                    master.move(d)
                    is_valid[(new_x, new_y)] = master.isTarget()
                    dfs(new_x, new_y)
                    master.move(anti[d])

        dfs(0, 0)
        queue = deque()
        queue.append((0, 0, 0))
        visited = set()
        while queue:
            size = len(queue)
            for _ in range(size):
                x, y, steps = queue.popleft()
                if is_valid[(x, y)] == True:
                    return steps

                for d in dirs:
                    d0, d1 = dirs[d]
                    new_x, new_y = x + d0, y + d1
                    if (new_x, new_y) in is_valid and (new_x, new_y) not in visited:
                        visited.add((new_x, new_y))
                        queue.append((new_x, new_y, steps + 1))

        return -1
