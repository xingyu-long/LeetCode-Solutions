from typing import List


class UnionFind:
    def __init__(self, n):
        self.n = n
        self.id = [i for i in range(n)]

    def find(self, root):
        if self.id[root] != root:
            self.id[root] = self.find(self.id[root])
        return self.id[root]

    def union(self, p: int, q: int):
        root_p = self.find(p)
        root_q = self.find(q)
        if root_p == root_q:
            return False
        self.id[root_p] = root_q
        return True


class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        uf = UnionFind(len(edges) + 1)
        for u, v in edges:
            if not uf.union(u, v):
                return [u, v]

        return []
