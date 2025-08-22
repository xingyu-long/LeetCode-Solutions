from typing import List


class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        res = []

        def backtracking(path, i):
            if len(path) == k:
                res.append(list(path))

            for j in range(i, n + 1):
                if len(path) > k:
                    continue
                path.append(j)
                backtracking(path, j + 1)
                path.pop()

        backtracking([], 1)
        return res
