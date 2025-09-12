from typing import List


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        x, y = click
        if board[x][y] == "M":
            board[x][y] = "X"
            return board

        rows, cols = len(board), len(board[0])
        # 8 directions
        d = [-1, 0, 1]

        def dfs(i, j):
            if not (0 <= i < rows and 0 <= j < cols):
                return
            if board[i][j] != "E":
                return
            nearby_mines = count_mines(i, j)
            if nearby_mines == 0:
                board[i][j] = "B"
                for n1 in d:
                    for n2 in d:
                        if n1 == 0 and n2 == 0:
                            continue
                        dfs(i + n1, j + n2)
            else:
                board[i][j] = str(nearby_mines)

        def count_mines(i, j):
            count = 0
            for n1 in d:
                for n2 in d:
                    if n1 == 0 and n2 == 0:
                        continue
                    new_i, new_j = i + n1, j + n2
                    if not (0 <= new_i < rows and 0 <= new_j < cols):
                        continue
                    if board[new_i][new_j] == "M":
                        count += 1

            return count

        dfs(x, y)
        return board
