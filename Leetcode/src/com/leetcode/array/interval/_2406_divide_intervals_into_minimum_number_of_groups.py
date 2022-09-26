import heapq
from typing import List


class Solution:
    # same as 253. Meeting Rooms II
    def minGroups(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x : x[0])
        heap = []
        for interval in intervals:
            if not heap:
                heapq.heappush(heap, interval[1])
            else:
                curr_end = heapq.heappop(heap)
                if interval[0] > curr_end:
                    # combine together
                    curr_end = interval[1]
                else:
                    # intersect
                    heapq.heappush(heap, interval[1])
                heapq.heappush(heap, curr_end)
            # print(interval, heap)
        return len(heap)