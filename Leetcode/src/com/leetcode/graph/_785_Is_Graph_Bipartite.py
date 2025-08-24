from collections import deque
from typing import List


class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        n = len(graph)
        queue = deque()
        color_mp = {}
        # start from every possible starting point
        for i in range(n):
            if i in color_mp:
                continue
            queue.append((i, 1))
            color_mp[i] = 1
            while queue:
                curr, color = queue.popleft()
                adjs = graph[curr]
                for adj in adjs:
                    if adj not in color_mp:
                        color_mp[adj] = -color
                        queue.append((adj, color_mp[adj]))
                    else:
                        if color_mp[adj] != -color:
                            return False
        return True
