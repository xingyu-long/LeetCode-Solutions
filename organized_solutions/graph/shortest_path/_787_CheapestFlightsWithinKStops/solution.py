from collections import defaultdict
from heapq import heappop, heappush
from math import inf
from typing import List


class Solution:
    def findCheapestPrice(
        self, n: int, flights: List[List[int]], src: int, dst: int, k: int
    ) -> int:
        # dijkstra -> timeout
        graph = defaultdict(dict)
        for flight in flights:
            u, v, w = flight
            graph[u][v] = w

        k += 1
        heap = []
        heappush(heap, (0, k, src))
        while heap:
            spend, stop, curr = heappop(heap)
            if curr == dst:
                return spend
            for adj, w in graph[curr].items():
                if stop > 0:
                    heappush(heap, (spend + w, stop - 1, adj))

        return -1


class Solution:
    def findCheapestPrice(
        self, n: int, flights: List[List[int]], src: int, dst: int, k: int
    ) -> int:

        dist = [inf] * n
        dist[src] = 0
        for _ in range(k + 1):
            # 一定要用这个临时数组，这样才能保证我们在一层一层的计算
            temp = dist.copy()
            # one layer at a time
            for flight in flights:
                u, v, w = flight
                if dist[u] == inf:
                    continue

                temp[v] = min(temp[v], dist[u] + w)
            dist = temp

        return -1 if dist[dst] == inf else dist[dst]
