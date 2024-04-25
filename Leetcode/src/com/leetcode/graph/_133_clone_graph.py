# Definition for a Node.
class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []


from collections import deque
from typing import Optional


class Solution:
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        if not node:
            return None

        queue = deque()
        m = dict()
        visited = set()
        queue.append(node)
        # build the dict: node -> new Node
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                if curr in visited:
                    continue
                visited.add(curr)
                if curr not in m:
                    m[curr] = Node(curr.val)

                for adj in curr.neighbors:
                    # build the relationship
                    if adj not in m:
                        m[adj] = Node(adj.val)
                    m[curr].neighbors.append(m[adj])
                    if adj not in visited:
                        queue.append(adj)

        return m[node]


class Solution2:
    # without visited set
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        if not node:
            return None

        queue = deque()
        m = dict()
        queue.append(node)
        m[node] = Node(node.val)
        # build the dict: node -> new Node
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()

                for adj in curr.neighbors:
                    # build the relationship
                    if adj not in m:
                        m[adj] = Node(adj.val)
                        queue.append(adj)
                    m[curr].neighbors.append(m[adj])

        return m[node]


class Solution3:
    # DFS
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        if not node:
            return None

        m = dict()

        def dfs(node):
            if node in m:
                return m[node]

            m[node] = Node(node.val)
            for adj in node.neighbors:
                adj_clone = dfs(adj)
                m[node].neighbors.append(adj_clone)

            return m[node]

        return dfs(node)
