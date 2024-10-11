from heapq import heappop, heappush
from typing import List


class Solution:
    # time: O(NlogN)
    # space: O(N)
    def smallestChair(self, times: List[List[int]], targetFriend: int) -> int:
        vals = []
        for i, (start, end) in enumerate(times):
            vals.append((start, 1, i))
            vals.append((end, -1, i))

        k = 0
        heap = []  # available seats
        m = {}  # player -> seat
        for _, state, i in sorted(vals):
            if state == 1:
                # arrive
                if heap:
                    assigned_seat = heappop(heap)
                else:
                    assigned_seat = k
                    k += 1
                if i == targetFriend:
                    return assigned_seat
                m[i] = assigned_seat
            else:
                # leave
                heappush(heap, m[i])

        return -1
