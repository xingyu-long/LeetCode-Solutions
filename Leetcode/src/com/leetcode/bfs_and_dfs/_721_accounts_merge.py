from collections import defaultdict
from typing import List


class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        graph = defaultdict(set)
        email_to_name = {}
        for acc in accounts:
            name = acc[0]
            for i in range(1, len(acc)):
                email = acc[i]
                email_to_name[email] = name
                if i == 1:
                    continue
                prev_email = acc[i - 1]
                graph[prev_email].add(email)
                graph[email].add(prev_email)

        def dfs(email, graph, visited, path):
            if email not in visited:
                visited.add(email)
                path.append(email)
                for adj in graph[email]:
                    dfs(adj, graph, visited, path)

        res = []
        visited = set()
        for email in email_to_name.keys():
            if email not in visited:
                path = []
                dfs(email, graph, visited, path)
                path.sort()
                path.insert(0, email_to_name[email])
                res.append(path)
        return res
