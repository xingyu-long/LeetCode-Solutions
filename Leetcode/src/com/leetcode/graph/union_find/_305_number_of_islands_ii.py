from typing import List


class UnionFind:
    def __init__(self, n):
        self.size = n
        self.id = [i for i in range(n)]

    def find(self, p):
        while self.id[p] != p:
            # path compression
            self.id[p] = self.id[self.id[p]]
            p = self.id[p]
        return p

    def union(self, p, q):
        self.id[self.find(p)] = self.find(q)


class Solution:
    # time: O(len(positions))
    def numIslands2(self, m: int, n: int, positions: List[List[int]]) -> List[int]:
        count = 0
        res = []
        uf = UnionFind(m * n)
        dirs = [[-1, 0], [0, -1], [1, 0], [0, 1]]
        is_island = [False] * (m * n)
        for x, y in positions:
            idx = x * n + y
            if is_island[idx]:
                # may have test cases where we repeat the positions
                res.append(count)
            else:
                count += 1
                is_island[idx] = True
                for d in dirs:
                    new_x, new_y = x + d[0], y + d[1]
                    new_idx = new_x * n + new_y
                    if (
                        new_x < 0
                        or new_x >= m
                        or new_y < 0
                        or new_y >= n
                        or not is_island[new_idx]
                    ):
                        continue
                    group1 = uf.find(idx)
                    group2 = uf.find(new_idx)
                    if group1 != group2:
                        uf.union(idx, new_idx)
                        count -= 1
                res.append(count)

        return res
