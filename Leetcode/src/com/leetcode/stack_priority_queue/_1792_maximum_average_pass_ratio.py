from heapq import heappop, heappush
from typing import List


class Solution:
    def maxAverageRatio(self, classes: List[List[int]], extraStudents: int) -> float:

        def profit(p: int, total: int) -> float:
            return (p + 1) / (total + 1) - p / total

        heap = []
        # max heap to track which one would be most profitable
        # after adding one extra into it.
        for p, total in classes:
            heappush(heap, (-profit(p, total), p, total))

        for _ in range(extraStudents):
            _, p, total = heappop(heap)
            heappush(heap, (-profit(p + 1, total + 1), p + 1, total + 1))

        return sum(p / total for _, p, total in heap) / len(classes)
