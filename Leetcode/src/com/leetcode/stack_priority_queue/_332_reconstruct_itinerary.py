from typing import List
from collections import defaultdict


class Solution:
    # time: O(Elog(E/V))
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        graph = defaultdict(list)
        for u, v in tickets:
            graph[u].append(v)

        for k, v in graph.items():
            v.sort(reverse=True)
        res = []

        def dfs(curr):
            nxts = graph[curr]
            while nxts:
                nxt = nxts.pop()
                dfs(nxt)
            res.append(curr)

        dfs("JFK")
        return res[::-1]
 