from heapq import heappop, heappush
from typing import List


class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        """
        |     |               |
        0     5              15
                        x         x       x
                        10        20      30
        """
        if not intervals:
            return 0
        res = 0
        starts = [i[0] for i in intervals]
        ends = [i[1] for i in intervals]
        starts.sort()
        ends.sort()
        end_i = 0
        for i in range(len(starts)):
            if starts[i] < ends[end_i]:
                res += 1
            else:
                end_i += 1
        return res


class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        if not intervals:
            return 0
        heap = []
        n = len(intervals)
        intervals.sort(key=lambda x: x[0])
        heappush(heap, intervals[0][1])
        for i in range(1, n):
            end = heappop(heap)
            # 可以接着房间用
            if intervals[i][0] >= end:
                _, end = intervals[i]
            else:
                # 这个就交叉了，一定需要新的房间
                heappush(heap, intervals[i][1])
            heappush(heap, end)
        return len(heap)
