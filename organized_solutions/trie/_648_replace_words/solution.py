from typing import List

from leetcode.common.py_utils import TrieNode


class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:

        def build_trie() -> TrieNode:
            root = TrieNode()

            for word in dictionary:
                curr = root
                for c in word:
                    if c not in curr.children:
                        curr.children[c] = TrieNode()
                    curr = curr.children[c]
                curr.is_word = True
                curr.word = word
            return root

        def find_word(root: TrieNode, word: str) -> str:
            curr = root
            for ch in word:
                if ch in curr.children:
                    curr = curr.children[ch]
                    if curr.is_word:
                        return curr.word
                else:
                    break
            return word

        root = build_trie()
        words = sentence.split(" ")
        res = []
        for word in words:
            res.append(find_word(root, word))

        return " ".join(res)
