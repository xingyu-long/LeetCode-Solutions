from collections import defaultdict
from typing import List


class Solution:
    # time: O(Elog(E/V))
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        graph = defaultdict(list)
        for u, v in tickets:
            graph[u].append(v)
        for _, v in graph.items():
            v.sort(reverse=True)

        res = []

        def build(start: str):
            while graph[start]:
                neighbor = graph[start].pop()
                build(neighbor)
            res.append(start)

        build("JFK")
        return res[::-1]

    # see DFS with backtracking: https://neetcode.io/solutions/reconstruct-itinerary

