'''
Date: 08/11/2022 17:14:55
LastEditTime: 08/11/2022 17:14:56
Description: Heapq
'''
from heapq import heappush, heappop


class MedianFinder:

    def __init__(self):
        self.min_heap = []
        self.max_heap = []

    def addNum(self, num: int) -> None:
        if len(self.min_heap) == len(self.max_heap):
            heappush(self.max_heap, -num)
            heappush(self.min_heap, -heappop(self.max_heap))
        else:
            heappush(self.min_heap, num)
            heappush(self.max_heap, -heappop(self.min_heap))

    def findMedian(self) -> float:

        if len(self.min_heap) == len(self.max_heap):
            res = self.min_heap[0] + (-self.max_heap[0])
            return res / 2.0
        else:
            return float(self.min_heap[0])
