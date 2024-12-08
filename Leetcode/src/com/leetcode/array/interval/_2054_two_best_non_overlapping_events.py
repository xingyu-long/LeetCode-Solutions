from heapq import heappop, heappush
from typing import List


class Solution:
    # time: O(NlogN)
    # space: O(N)
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        # interval
        if not events:
            return 0
        heap = []
        events.sort()
        res = max_val = 0
        for start, end, val in events:
            while heap and heap[0][0] < start:
                max_val = max(max_val, heap[0][1])
                heappop(heap)
            res = max(res, max_val + val)
            heappush(heap, (end, val))

        return res
