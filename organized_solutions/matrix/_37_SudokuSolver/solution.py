'''
Date: 08/22/2021 19:50:16
LastEditTime: 09/05/2022 16:04:31
Description: DFS
'''
from typing import List


class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:

        def is_valid(board, row, col, ch):
            for y in board[row]:
                if y == ch:
                    return False

            for x in [arr[col] for arr in board]:
                if x == ch:
                    return False

            for i in range(0, 9):
                if board[3 * (row // 3) + i // 3][3 * (col // 3) + i % 3] != '.' and board[3 * (row // 3) + i // 3][3 * (col // 3) + i % 3] == ch:
                    return False

            return True

        def solve(board, row, col):
            if col == len(board[0]):
                row += 1
                col = 0
                if row == len(board):
                    return True

            if board[row][col] == '.':
                for x in range(1, 10):
                    if is_valid(board, row, col, str(x)):
                        board[row][col] = str(x)
                        if solve(board, row, col + 1):
                            return True
                        board[row][col] = '.'
            else:
                return solve(board, row, col + 1)

        solve(board, 0, 0)
