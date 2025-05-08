from heapq import heappop, heappush
from math import inf
from typing import List


class Solution:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        # heap based
        m, n = len(moveTime), len(moveTime[0])
        heap = []
        # curr_take for [0, 0] should be 0 or None to represent
        heappush(heap, (0, 0, 0, 0))
        dist = [[inf] * n for _ in range(m)]
        dist[0][0] = 0
        dirs = [(-1, 0), (0, -1), (1, 0), (0, 1)]
        while heap:
            # four dirs
            min_time, i, j, curr_take = heappop(heap)
            if i == m - 1 and j == n - 1:
                return min_time
            for x, y in dirs:
                new_x, new_y = i + x, j + y
                if 0 <= new_x < m and 0 <= new_y < n:
                    nxt_take = 2 if curr_take == 1 else 1
                    nxt_time = max(min_time, moveTime[new_x][new_y]) + nxt_take
                    if nxt_time < dist[new_x][new_y]:
                        dist[new_x][new_y] = nxt_time
                        heappush(heap, (nxt_time, new_x, new_y, nxt_take))
        return -1
