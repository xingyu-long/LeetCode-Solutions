'''
Date: 12/28/2021 15:04:46
LastEditTime: 12/28/2021 15:16:19
Description: Sort, Max Heap
'''

from typing import List
import heapq


class Solution:
    # Sort
    # time: O(NlogN)
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        return sorted(points, key=lambda point: point[0] ** 2 + point[1] ** 2)[:k]

    # Max Heap
    # O(Nlogk)
    def kClosest2(self, points: List[List[int]], k: int) -> List[List[int]]:
        heap, dist = [], lambda x, y: x * x + y * y
        for i, (x, y) in enumerate(points):
            d = dist(x, y)
            if len(heap) == k:
                heapq.heappushpop(heap, (-d, i))
            else:
                heapq.heappush(heap, (-d, i))
        return [points[i] for (_, i) in heap]
