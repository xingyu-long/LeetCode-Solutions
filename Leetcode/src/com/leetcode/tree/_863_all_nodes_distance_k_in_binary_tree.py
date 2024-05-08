from collections import defaultdict, deque
from typing import List
from leetcode.common.py_utils import TreeNode


class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        if not root:
            return None

        def build_graph(graph, root):
            if not root:
                return

            if root.left:
                graph[root.val].add(root.left.val)
                graph[root.left.val].add(root.val)
            if root.right:
                graph[root.val].add(root.right.val)
                graph[root.right.val].add(root.val)

            build_graph(graph, root.left)
            build_graph(graph, root.right)

        graph = defaultdict(set)
        build_graph(graph, root)
        # BFS
        res = []
        queue = deque()
        queue.append((target.val, k))
        visited = set([target.val])
        while queue:
            size = len(queue)
            for _ in range(size):
                curr, stops = queue.popleft()
                if stops == 0:
                    res.append(curr)
                    continue
                for adj in graph[curr]:
                    if adj not in visited and stops > 0:
                        visited.add(adj)
                        queue.append((adj, stops - 1))

        return res
