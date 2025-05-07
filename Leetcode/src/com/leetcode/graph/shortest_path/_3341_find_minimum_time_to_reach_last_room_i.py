from heapq import heappop, heappush
from math import inf
from typing import List


class Solution:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        """
        dp = [[0] * cols for _ in range(rows)]
        dp[0][0] = 0
        for i in range(1, rows):
            dp[i][0] = max(moveTime[i][0] + 1, dp[i - 1][0] + 1)
        for j in range(1, cols):
            dp[0][j] = max(moveTime[0][j] + 1, dp[0][j - 1] + 1)

        # print(dp)
        for i in range(1, rows):
            for j in range(1, cols):
                dp[i][j] = max(min(dp[i - 1][j], dp[i][j - 1]) + 1, moveTime[i][j] + 1)

        # print(dp)
        return dp[rows - 1][cols - 1]

        above solution won't work if we go up and downs
        example
        [94, 79, 62, 27, 69, 84]
        [6,  32, 11, 82, 42, 30]
        6 -> 32 -> 11 -> 62 -> 27 -> 69 -> 42 -> 30
        """
        # heap based
        m, n = len(moveTime), len(moveTime[0])
        heap = []
        heappush(heap, (0, 0, 0))
        dist = [[inf] * n for _ in range(m)]
        dist[0][0] = 0
        dirs = [(-1, 0), (0, -1), (1, 0), (0, 1)]
        while heap:
            # four dirs
            min_time, i, j = heappop(heap)
            if i == m - 1 and j == n - 1:
                return min_time
            for x, y in dirs:
                new_x, new_y = i + x, j + y
                if 0 <= new_x < m and 0 <= new_y < n:
                    nxt_time = max(min_time + 1, moveTime[new_x][new_y] + 1)
                    if nxt_time < dist[new_x][new_y]:
                        dist[new_x][new_y] = nxt_time
                        heappush(heap, (nxt_time, new_x, new_y))
        return -1
