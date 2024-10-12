import heapq
from typing import List


class Solution:
    # same as 253. Meeting Rooms II
    def minGroups(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: x[0])
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
        return len(heap)


class Solution2:
    def minGroups(self, intervals: List[List[int]]) -> int:
        vals = []
        for _, (s, e) in enumerate(intervals):
            vals.append((s, 1))
            vals.append((e, -1))

        vals.sort(key=lambda x: (x[0], -x[1]))
        res = 0
        count = 0
        for _, state in vals:
            count += state
            res = max(res, count)

        return res

