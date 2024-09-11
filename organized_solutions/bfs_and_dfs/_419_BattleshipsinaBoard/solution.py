from typing import List


class Solution:
    def countBattleships(self, board: List[List[str]]) -> int:
        m, n = len(board), len(board[0])
        res = 0
        for i in range(m):
            for j in range(n):
                if board[i][j] == ".":
                    continue
                if j - 1 >= 0 and board[i][j - 1] == "X":
                    continue
                if i - 1 >= 0 and board[i - 1][j] == "X":
                    continue

                res += 1
        return res
