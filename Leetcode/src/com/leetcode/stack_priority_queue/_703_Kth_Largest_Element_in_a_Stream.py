'''
Date: 04/20/2022 15:45:18
LastEditTime: 04/20/2022 15:45:18
Description: heapq
'''
from typing import List
from heapq import heappush, heappop


class KthLargest:
    def __init__(self, k: int, nums: List[int]):
        self.k = k
        self.h = []
        for num in nums:
            heappush(self.h, num)
            if len(self.h) > k:
                heappop(self.h)

    def add(self, val: int) -> int:
        heappush(self.h, val)
        if len(self.h) > self.k:
            heappop(self.h)
        return self.h[0]
