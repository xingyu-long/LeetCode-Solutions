from typing import List


class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        m = len(board)
        n = len(board[0])
        visited = dict()

        def dfs(i: int, j: int, word_idx: int) -> bool:
            # to handle the case like [[a]], so we need to compare with word first.
            if word_idx == len(word):
                return True
            if i < 0 or i >= m or j < 0 or j >= n:
                return False
            if board[i][j] != word[word_idx]:
                return False
            key = (i, j)
            if key in visited and visited[key]:
                return False
            visited[key] = True
            left = dfs(i - 1, j, word_idx + 1)
            right = dfs(i + 1, j, word_idx + 1)
            up = dfs(i, j + 1, word_idx + 1)
            down = dfs(i, j - 1, word_idx + 1)
            visited[key] = False
            return left or right or up or down

        visited = {}
        for i in range(m):
            for j in range(n):
                # try every possible locations to start
                if dfs(i, j, visited, word, 0):
                    return True
        return False
