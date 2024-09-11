from heapq import heappop, heappush
from typing import List


class Solution:

    # time: O(Nlogk)
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        distance = lambda x, y: x**2 + y**2
        heap = []
        for idx, p in enumerate(points):
            heappush(heap, (-distance(p[0], p[1]), idx))
            if len(heap) > k:
                heappop(heap)
        return [points[idx] for _, idx in heap]
