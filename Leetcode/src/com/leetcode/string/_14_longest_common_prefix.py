from typing import List


class TrieNode:
    def __init__(self):
        self.children = {}
        self.isEnd = False
        self.linkCount = 0

    def addChildren(self, ch):
        if ch not in self.children:
            self.children[ch] = TrieNode()
            self.linkCount += 1

    def __repr__(self):
        return f"TrieNode: children={self.children}, isEnd={self.isEnd}, linkCount={self.linkCount}"


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        curr = self.root
        for ch in word:
            if ch not in curr.children:
                curr.addChildren(ch)
            curr = curr.children[ch]
        curr.isEnd = True

    def search_prefix(self, word):
        curr = self.root
        res = []
        for ch in word:
            # 每次提前看下一个的字符，所以curr还没有到结尾
            if ch in curr.children and curr.linkCount == 1 and not curr.isEnd:
                res.append(ch)
                curr = curr.children[ch]
            else:
                break
        return "".join(res)


class Solution:
    """
    build the trie:
        time: O(len(str) * n)
        space: O(unique chars)
    search the trie:
        time: O(len(str))
    """

    def longestCommonPrefix(self, strs: List[str]) -> str:
        # Trie
        if not strs:
            return ""

        if len(strs) == 1:
            return strs[0]

        trie = Trie()
        for s in strs[1:]:
            trie.insert(s)
        return trie.search_prefix(strs[0])


solution = Solution()
assert solution.longestCommonPrefix(["dog", "dogg", "dog"]) == "dog"
