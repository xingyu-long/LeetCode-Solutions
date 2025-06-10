from leetcode.common.py_utils import TrieNode


class WordDictionary:
    def __init__(self):
        self.root = TrieNode()

    def addWord(self, word: str) -> None:
        curr = self.root
        for ch in word:
            if ch not in curr.children:
                curr.children[ch] = TrieNode()
            curr = curr.children[ch]
        curr.is_word = True
        curr.word = word

    def _search(self, curr: TrieNode, word: str, index: int) -> bool:
        if index > len(word):
            return False

        if index == len(word):
            # NOTE: don't need to check with word, since it's not exact match.
            return curr.is_word

        ch = word[index]
        if ch == ".":
            for k in curr.children:
                if self._search(curr.children[k], word, index + 1):
                    return True
            return False
        else:
            if ch not in curr.children:
                return False
            # move forward with next children
            return self._search(curr.children[ch], word, index + 1)

    def search(self, word: str) -> bool:
        return self._search(self.root, word, 0)
