from collections import Counter, deque
from heapq import heapify, heappop, heappush
from typing import List


class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        counter = Counter(tasks)
        heap = [-cnt for cnt in counter.values()]
        heapify(heap)
        queue = deque() # (count, wait until then, i.e. cool down)
        time = 0

        while heap or queue:
            if heap:
                count = 1 + heappop(heap)
                if abs(count) > 0:
                    # print(f"count={count}")
                    queue.append((count, time + n))
            
            if queue and time == queue[0][1]:
                heappush(heap, queue.popleft()[0])

            time += 1

        return time
