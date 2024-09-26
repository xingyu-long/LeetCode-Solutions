class TrieNode:
    def __init__(self):
        self.children = dict()
        self.count = 0


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str):
        curr = self.root
        for ch in word:
            if ch not in curr.children:
                curr.children[ch] = TrieNode()
            # whenever we travsal here
            curr.children[ch].count += 1
            curr = curr.children[ch]

    def compute_prefix_score(self, word: str):
        curr = self.root
        total = 0
        for ch in word:
            if ch not in curr.children:
                break
            total += curr.children[ch].count
            curr = curr.children[ch]
        return total


class Solution:
    def sumPrefixScores(self, words: List[str]) -> List[int]:
        t = Trie()
        for word in words:
            t.insert(word)

        return [t.compute_prefix_score(word) for word in words]
