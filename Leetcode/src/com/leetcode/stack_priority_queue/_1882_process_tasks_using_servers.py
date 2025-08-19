from heapq import heappop, heappush
from typing import List


class Solution:
    # Time: O(N + M) logN
    # N: len(servers)
    # M: len(tasks)
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        # [weight, index, available_time]
        available_servers = []

        # [available_time, weight, index]
        busy_servers = []
        for i, weight in enumerate(servers):
            heappush(available_servers, (weight, i, 0))

        res = []

        for i, task_time in enumerate(tasks):
            # i-th second
            while busy_servers and busy_servers[0][0] <= i:
                available_time, weight, index = heappop(busy_servers)
                heappush(available_servers, (weight, index, available_time))

            if available_servers:
                weight, index, available_time = heappop(available_servers)
                res.append(index)
                available_time = i + task_time
                heappush(busy_servers, (available_time, weight, index))
            else:
                # WARN: we don't care about each timestamp.
                #
                # there is no available servers to assign
                # assuming that we already waited first server from busy_servers
                # so now, we assign it again.
                available_time, weight, index = heappop(busy_servers)
                res.append(index)
                available_time += task_time
                heappush(busy_servers, (available_time, weight, index))

        return res
