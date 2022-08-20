'''
Date: 08/14/2022 17:16:30
LastEditTime: 08/14/2022 17:19:05
Description: BFS, graph
'''
from collections import defaultdict, deque
from typing import List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:

    # time: O(N + k * edges -> N)
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        d = defaultdict(lambda: set())

        def build(root, prev):
            if not root:
                return

            if prev:
                d[prev.val].add(root.val)
                d[root.val].add(prev.val)

            build(root.left, root)
            build(root.right, root)

        build(root, None)
        res = []
        queue = deque()
        queue.append((target.val, k))
        visited = set()
        visited.add(target.val)
        while len(queue):
            size = len(queue)
            for _ in range(size):
                curr, step = queue.popleft()
                if step == 0:
                    res.append(curr)
                for adj in d[curr]:
                    if adj not in visited:
                        queue.append((adj, step - 1))
                        visited.add(adj)
        return res
