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


class Solution2:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        if not intervals:
            return 0

        intervals.sort(key=lambda x: x[0])
        heap = []
        heappush(heap, (intervals[0][1]))
        for i in range(1, len(intervals)):
            curr_end = heappop(heap)
            if intervals[i][0] < curr_end:
                heappush(heap, (intervals[i][1]))
            else:
                curr_end = intervals[i][1]
            heappush(heap, (curr_end))

        return len(heap)


class Solution3:
    # sweep line
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        if not intervals:
            return 0

        arr = []
        for start, end in intervals:
            arr.append([start, 1])
            arr.append([end, -1])

        # 需要把-1排到前面来。[[13,15],[1,13]]
        arr.sort(key=lambda x: (x[0], x[1]))

        res, count = 0, 0
        for _, op in arr:
            count += op
            res = max(res, count)
        return res
