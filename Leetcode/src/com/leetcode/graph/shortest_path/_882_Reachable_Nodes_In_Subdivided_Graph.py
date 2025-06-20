from collections import defaultdict
from heapq import heappop, heappush
from typing import List


class Solution:
    def reachableNodes(self, edges: List[List[int]], maxMoves: int, n: int) -> int:
        # undirected graph
        graph = [[-1] * n for _ in range(n)]
        for u, v, w in edges:
            graph[u][v] = graph[v][u] = w

        visited = set()
        heap = [(-maxMoves, 0)]
        res = 0
        while heap:
            curr_left, curr = heappop(heap)
            moves = -curr_left
            if curr in visited:
                continue
            # visited curr location and add 1 into result.
            visited.add(curr)
            res += 1
            for i in range(n):
                # no edge from curr to i
                if graph[curr][i] == -1:
                    continue
                if moves > graph[curr][i] and i not in visited:
                    # we need to reduce 1 more due to the V[i]
                    new_left = moves - graph[curr][i] - 1
                    heappush(heap, (-new_left, i))
                # we either use all moves or graph[curr][i] if graph[curr][i] < moves
                cost = min(moves, graph[curr][i])
                # prevent going back, i.e. from i to curr
                graph[i][curr] -= cost
                res += cost
        return res

