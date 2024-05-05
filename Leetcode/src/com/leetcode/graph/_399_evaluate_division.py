from typing import List


class Solution:
    # time:O(V + E) space:O(n)
    def calcEquation(
        self, equations: List[List[str]], values: List[float], queries: List[List[str]]
    ) -> List[float]:
        graph = {}
        for i in range(len(values)):
            first, second = equations[i]
            if first not in graph:
                graph[first] = {}
            graph[first][second] = values[i]
            if second not in graph:
                graph[second] = {}
            graph[second][first] = 1.0 / values[i]

        # 这里并不需要backtracking
        def dfs(start, end, visited):
            if start not in graph or end not in graph:
                return -1.0
            if start == end:
                return 1

            visited.add(start)
            for adj in graph[start]:
                if adj not in visited:
                    curr_val = graph[start][adj]
                    next_val = dfs(adj, end, visited)
                    if next_val > 0:
                        return curr_val * next_val
            return -1.0

        res = []
        for q in queries:
            curr = dfs(q[0], q[1], set())
            res.append(curr)
        return res
