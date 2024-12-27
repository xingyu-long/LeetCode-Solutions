from typing import List


class Solution:
    def minimumDiameterAfterMerge(
        self, edges1: List[List[int]], edges2: List[List[int]]
    ) -> int:
        def build_graph(edges):
            graph = defaultdict(list)
            for u, v in edges:
                graph[u].append(v)
                graph[v].append(u)

            return graph

        def bfs(graph, start):
            n = len(graph)
            visited = [False] * n
            visited[start] = True
            queue = deque()
            queue.append((start, 1))
            farthest_index = start
            longest = 1
            while queue:
                size = len(queue)
                for _ in range(size):
                    curr, curr_len = queue.popleft()
                    for nxt in graph[curr]:
                        if not visited[nxt]:
                            visited[nxt] = True
                            if curr_len + 1 > longest:
                                farthest_index = nxt
                                longest = curr_len + 1
                            queue.append((nxt, curr_len + 1))

            return farthest_index, longest - 1

        def diameter(edges):
            if not edges:
                return 0

            graph = build_graph(edges)
            # !!! this is the key for the problem here!!!
            # 1) start from random position and go farthest
            index, _ = bfs(graph, 0)
            # 2) and then try to use the index to start again
            _, longest = bfs(graph, index)
            return longest

        d1 = diameter(edges1)
        d2 = diameter(edges2)
        return max(d1, d2, (d1 + 1) // 2 + (d2 + 1) // 2 + 1)
        # print(d1, d2)
