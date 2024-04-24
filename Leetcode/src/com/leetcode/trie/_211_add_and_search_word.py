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

    def search(self, word: str) -> bool:

        def dfs(index, curr):
            if index == len(word):
                return curr.is_word

            if word[index] == ".":
                # iterate all possible keys
                for key in curr.children.keys():
                    if dfs(index + 1, curr.children[key]):
                        return True
            else:
                if word[index] not in curr.children:
                    return False
                curr = curr.children[word[index]]
                return dfs(index + 1, curr)

        curr = self.root
        return dfs(0, curr)
