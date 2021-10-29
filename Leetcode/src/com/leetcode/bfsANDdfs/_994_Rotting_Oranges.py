'''
Date: 10/29/2021 09:49:40
LastEditTime: 10/29/2021 10:25:38
Description: You need to fill out
'''
from queue import Queue
from typing import List


class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        # BFS
        # 1、 一开始没有注意到使用fresh来统计
        # 2、没有每层遍历（qsize())

        m, n = len(grid), len(grid[0])
        q = Queue()
        fresh = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 2:
                    # Add rotten orange and expand them.
                    q.put((i, j))
                elif grid[i][j] == 1:
                    fresh += 1
        if fresh == 0:
            return 0
        steps = -1
        dirs = [[-1, 0], [0, -1], [1, 0], [0, 1]]
        while not q.empty():
            for _ in range(q.qsize()):
                x, y = q.get()
                for d in dirs:
                    new_x = x + d[0]
                    new_y = y + d[1]

                    if new_x < 0 or new_x >= m or new_y < 0 or new_y >= n:
                        continue
                    if grid[new_x][new_y] != 1:
                        continue

                    # mark and and to next level
                    fresh -= 1
                    grid[new_x][new_y] = 2
                    q.put((new_x, new_y))
            steps += 1

        return steps if fresh == 0 else -1

grid = [[2,1,1],[1,1,0],[0,1,1]]
result = Solution().orangesRotting(grid)
print(result)