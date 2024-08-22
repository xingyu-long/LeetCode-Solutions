from typing import List
from collections import deque, defaultdict


class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        queue = deque()
        queue.append(0)
        graph = defaultdict(set)
        for u, v in edges:
            graph[u].add(v)
            graph[v].add(u)

        visited = set()

        def dfs(curr):
            visited.add(curr)
            total = 0
            if len(graph[curr]) == 0:
                return total
            for adj in graph[curr]:
                if adj in visited:
                    continue
                cost = dfs(adj)
                if cost > 0 or hasApple[adj]:
                    total += cost + 2
            return total

        return dfs(0)
