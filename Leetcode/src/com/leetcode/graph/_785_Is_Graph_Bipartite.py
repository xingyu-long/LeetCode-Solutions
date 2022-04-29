from typing import List
from collections import deque


class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        if not graph:
            return True
        d = dict()
        q = deque()
        # Go through each possible start point
        for i in range(len(graph)):
            if i in d:
                continue
            # Use i as start point
            q.append(i)
            d[i] = 1
            while len(q) > 0:
                current = q.pop()
                # Find next level
                adj_nodes = graph[current]
                next_label = d[current] * -1
                for node in adj_nodes:
                    if node in d:
                        if d[node] != next_label:
                            return False
                    else:
                        d[node] = next_label
                        q.append(node)
        return True
