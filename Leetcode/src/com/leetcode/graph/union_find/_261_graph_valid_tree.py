from typing import List

from common.py_utils import UnionFind


class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        uf = UnionFind(n)
        for u, v in edges:
            if not uf.union(u, v):
                return False

        return uf.count == 1
