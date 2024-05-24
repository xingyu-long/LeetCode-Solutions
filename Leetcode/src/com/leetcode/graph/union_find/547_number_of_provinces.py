from typing import List


class UnionFind:
    def __init__(self, n: int):
        self.size = n
        self.id = [i for i in range(n)]

    def find(self, root) -> int:
        if self.id[root] != root:
            self.id[root] = self.find(self.id[root])
        return self.id[root]

    def union(self, p: int, q: int) -> bool:
        p_root = self.find(p)
        q_root = self.find(q)
        if p_root == q_root:
            return False
        self.id[p_root] = q_root
        return True


class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        n = len(isConnected)
        uf = UnionFind(n)
        total = n
        for i in range(n):
            for j in range(n):
                if i == j:
                    continue
                if isConnected[i][j] == 1:
                    if uf.union(i, j):
                        total -= 1
        return total
