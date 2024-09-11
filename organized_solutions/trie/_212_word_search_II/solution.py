from typing import List
from leetcode.common.py_utils import TrieNode


class Solution:

    # Use Trie to avoid iterating words for the loop.
    # With Trie, we can try every position on board and keep looking based on Trie.
    # m*n*len(word)*#of word -> m*n*max(len(word))
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        # backtracking and use trie to stop it earlier.
        m, n = len(board), len(board[0])
        visited = [[False] * n for _ in range(m)]
        res = []

        def build_trie():
            root = TrieNode()
            for word in words:
                curr = root
                for ch in word:
                    if ch not in curr.children:
                        curr.children[ch] = TrieNode()
                    curr = curr.children[ch]
                curr.is_word = True
                curr.word = word
            return root

        def dfs(curr, row, col):
            if row < 0 or row >= m or col < 0 or col >= n:
                return
            if visited[row][col]:
                return
            ch = board[row][col]
            if ch not in curr.children:
                return
            curr = curr.children[ch]
            if curr.is_word:
                res.append(curr.word)
                # remove the mark since we already found this word
                curr.is_word = False

            visited[row][col] = True
            dfs(curr, row, col - 1)
            dfs(curr, row, col + 1)
            dfs(curr, row - 1, col)
            dfs(curr, row + 1, col)
            visited[row][col] = False

        # main logic
        root = build_trie()
        for row in range(m):
            for col in range(n):
                dfs(root, row, col)
        return res
