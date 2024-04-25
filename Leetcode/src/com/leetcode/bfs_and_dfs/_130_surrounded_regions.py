from typing import List


class Solution:
    # try with unsurrounded area and run it backwards
    # all other O must be surrounded.
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m, n = len(board), len(board[0])

        def dfs(i, j):
            if not (0 <= i < m) or not (0 <= j < n):
                return
            if board[i][j] != "O":
                return
            board[i][j] = "#"
            dfs(i, j + 1)
            dfs(i, j - 1)
            dfs(i + 1, j)
            dfs(i - 1, j)

        # 1. mark the unsurrounded regions as #
        for i in range(m):
            dfs(i, 0)
            dfs(i, n - 1)
        for j in range(n):
            dfs(0, j)
            dfs(m - 1, j)
        for i in range(m):
            for j in range(n):
                # 2. mark all O to X
                if board[i][j] == "O":
                    board[i][j] = "X"
                # 3. restore # to O
                elif board[i][j] == "#":
                    board[i][j] = "O"
