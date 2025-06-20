from collections import defaultdict, deque
from heapq import heappop, heappush
from math import inf
from typing import List


class Solution:
    # time: O(V * E)
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # bellman ford
        dist = [inf] * (n + 1)
        dist[k] = 0
        for i in range(n):
            temp = dist.copy()
            for time in times:
                u, v, w = time
                # check if we have shorter path from u to v
                if dist[u] == inf:
                    continue
                temp[v] = min(temp[v], dist[u] + w)
            dist = temp

        res = -inf
        for i in range(1, n + 1):
            res = max(res, dist[i])
        return -1 if res == inf else res


class Solution2:
    # Floyd–Warshall's Algorithm
    # time: O(V^3)
    # space: O(V^2)
    def networkDelayTime(self, times: List[List[int]], n: int, K: int) -> int:
        dist = [[inf] * (n + 1) for _ in range(n + 1)]
        for time in times:
            u, v, w = time
            dist[u][v] = w

        for k in range(1, n + 1):
            for i in range(1, n + 1):
                for j in range(1, n + 1):
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

        res = -inf
        for i in range(1, n + 1):
            if i == K:
                continue
            res = max(res, dist[K][i])

        return -1 if res == inf else res


class Solution3:
    # Dijkstra's based on heap
    # time: O((V+E)*logV)
    # space: O(V)
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        graph = defaultdict(dict)
        for time in times:
            u, v, w = time
            graph[u][v] = w

        dist = [inf] * (n + 1)
        heap = []
        heappush(heap, (0, k))
        visited = set()
        while heap:
            dis, curr = heappop(heap)
            if curr in visited:
                continue
            visited.add(curr)
            dist[curr] = dis
            for adj, w in graph[curr].items():
                if dis + w < dist[adj]:
                    heappush(heap, (dis + w, adj))

        res = -inf
        for i in range(1, n + 1):
            res = max(res, dist[i])
        return -1 if res == inf else res


class Solution4:
    # time: O(V*E)
    # space: O(V)
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        dist = [inf] * (n + 1)
        graph = defaultdict(list)
        for u, v, w in times:
            graph[u].append((v, w))
        dist[k] = 0
        queue = deque()
        queue.append((k, 0))
        while queue:
            size = len(queue)
            for _ in range(size):
                curr, curr_w = queue.popleft()
                for neigh, w in graph[curr]:
                    new_w = curr_w + w
                    if new_w < dist[neigh]:
                        dist[neigh] = new_w
                        queue.append((neigh, new_w))

        res = max(dist[1:])
        return res if res != inf else -1
