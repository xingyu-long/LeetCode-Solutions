from heapq import heappop, heappush
from typing import List


class Solution:
    def minInterval(self, intervals: List[List[int]], queries: List[int]) -> List[int]:
        intervals.sort(key=lambda x: x[0])
        res, i = {}, 0
        n = len(intervals)
        heap = []
        for q in sorted(queries):
            # add all intervals which include q
            while i < n and q >= intervals[i][0]:
                start, end = intervals[i]
                heappush(heap, (end - start + 1, end))
                i += 1

            # pop out all potential intervals which doesn't include q
            while heap and q > heap[0][1]:
                heappop(heap)

            res[q] = heap[0][0] if heap else -1

        return [res[q] for q in queries]
