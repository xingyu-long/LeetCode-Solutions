from collections import defaultdict, deque
from typing import List


class Solution:
    def alienOrder(self, words: List[str]) -> str:

        n = len(words)
        graph = dict()
        indegree = defaultdict(int)

        def build_graph(graph, indegree):
            for word in words:
                for ch in word:
                    graph[ch] = set()

            for i in range(1, n):
                prev = words[i - 1]
                curr = words[i]
                if prev.startswith(curr) and len(prev) > len(curr):
                    return False
                for j in range(min(len(prev), len(curr))):
                    c1, c2 = prev[j], curr[j]
                    print(c1, c2)
                    if c1 != c2:
                        if c2 not in graph[c1]:
                            graph[c1].add(c2)
                            indegree[c2] += 1
                        break

            return True

        def bfs(graph, indegree):
            queue = deque()
            for k in graph:
                if indegree[k] == 0:
                    queue.append(k)
            res = []
            total = len(graph)
            while queue:
                size = len(queue)
                for _ in range(size):
                    curr = queue.popleft()
                    res.append(curr)
                    for adj in graph[curr]:
                        indegree[adj] -= 1
                        if indegree[adj] == 0:
                            queue.append(adj)

            return "".join(res) if total == len(res) else ""

        succ = build_graph(graph, indegree)
        if not succ:
            return ""
        return bfs(graph, indegree)
