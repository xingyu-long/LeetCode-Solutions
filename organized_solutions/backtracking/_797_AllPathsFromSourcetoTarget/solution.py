'''
Date: 11/27/2021 20:47:56
LastEditTime: 11/27/2021 20:59:18
Description: Backtracking without visited variable.
'''


from typing import List


class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        # DFS traversal
        # order is important.
        res = []

        def dfs(graph, current, path):
            path.append(current)
            if current == len(graph) - 1:
                res.append(list(path))

            adj_list = graph[current]
            for adj in adj_list:
                # go to next level
                dfs(graph, adj, path)
            path.pop()

        dfs(graph, 0, [])
        return res
