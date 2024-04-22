from heapq import heapify, heappop, heappush
from typing import List


class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        # max heap
        heap = [-s for s in stones]
        heapify(heap)
        while len(heap) > 1:
            first = -heappop(heap)
            second = -heappop(heap)
            if first == second:
                continue
            else:
                heappush(heap, -abs(first - second))
        return -heap[0] if heap else 0
