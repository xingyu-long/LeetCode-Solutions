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


class Solution2:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        email_name = {}
        # union find
        parent = {}
        # parent -> set of emails
        union = defaultdict(set)

        def find(p, parent):
            while p != parent[p]:
                p = parent[p]
            return p

        for acc in accounts:
            name = acc[0]
            for email in acc[1:]:
                email_name[email] = name
                parent[email] = email

        # union
        for acc in accounts:
            p = find(acc[1], parent)
            for email in acc[2:]:
                parent[find(email, parent)] = p

        # use acc[1] as parent and add the rest of emails into this union
        for acc in accounts:
            p = find(acc[1], parent)
            for email in acc[1:]:
                union[p].add(email)

        res = []
        for p in union:
            curr = sorted(list(union[p]))
            curr.insert(0, email_name[p])
            res.append(list(curr))
        return res
