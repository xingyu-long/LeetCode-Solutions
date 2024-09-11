from typing import List


class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        res = []

        def generate_table(path):
            return ["".join(["Q" if j == i else "." for j in range(n)]) for i in path]

        def is_valid(path, new_pos):
            new_row = len(path)
            for i in range(len(path)):
                if abs(new_row - i) == abs(new_pos - path[i]) or new_pos == path[i]:
                    return False
            return True

        def dfs(path, row):
            if row == n:
                res.append(generate_table(list(path)))
                return

            for i in range(n):
                if is_valid(path, i):
                    path.append(i)
                    dfs(path, row + 1)
                    path.pop()

        dfs([], 0)
        return res
