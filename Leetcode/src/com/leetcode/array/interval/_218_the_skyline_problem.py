from typing import List
from heapq import heappush, heappop


class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        events = []
        for L, R, H in buildings:
            events.append((L, -H, R))
            events.append((R, 0, 0))

        events.sort()
        prev_max = 0
        heap = [(0, float("inf"))]
        res = []

        for pos, H, R in events:
            # remove previous buildings
            while heap[0][1] <= pos:
                heappop(heap)

            if H != 0:
                heappush(heap, (H, R))

            curr_max = -heap[0][0]
            if prev_max != curr_max:
                res.append([pos, curr_max])
                prev_max = curr_max

        return res
