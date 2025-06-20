from collections import defaultdict
from heapq import heappop, heappush
from typing import List


class Solution:
    # Prim's algorithm
    # time: O(N^2 * LogN)
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        manhattan = lambda p1, p2: abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])
        graph = defaultdict(list)
        n = len(points)

        # 1. list all possible edges
        for i in range(n):
            for j in range(i + 1, n):
                dist = manhattan(points[i], points[j])
                graph[i].append((dist, j))
                graph[j].append((dist, i))

        # 2. min heap with visited array
        res = 0
        visited = set()
        # start from point 0, it can be any point
        heap = [(0, 0)]  # (cost, point)
        while len(visited) < n:
            cost, curr = heappop(heap)
            if curr in visited:
                continue
            visited.add(curr)
            res += cost
            for dist, neighbor in graph[curr]:
                if neighbor not in visited:
                    heappush(heap, (dist, neighbor))

        return res
