from typing import List
from collections import deque


class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        n = len(graph)
        queue = deque()
        m = {}
        # start from every possible starting point
        for i in range(n):
            if i in m:
                continue
            queue.append((i, 1))
            m[i] = 1
            while queue:
                curr, color = queue.popleft()
                adjs = graph[curr]
                for adj in adjs:
                    if adj not in m:
                        m[adj] = -color
                        queue.append((adj, m[adj]))
                    else:
                        if m[adj] != -color:
                            return False
        return True
