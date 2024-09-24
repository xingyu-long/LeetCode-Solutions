class TrieNode:
    def __init__(self):
        self.children = dict()
        self.is_word = False


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word) -> None:
        curr = self.root
        for ch in word:
            if ch not in curr.children:
                curr.children[ch] = TrieNode()
            curr = curr.children[ch]
        curr.is_word = True

    def find_common(self, word) -> int:
        count = 0
        curr = self.root
        for ch in word:
            if ch in curr.children:
                curr = curr.children[ch]
                count += 1
            else:
                break
        return count


class Solution:
    # time: O(# of words * len(word))
    def longestCommonPrefix(self, arr1: List[int], arr2: List[int]) -> int:
        if not arr1 or not arr2:
            return 0

        # implement it with Trie
        t = Trie()
        for num in arr1:
            t.insert(str(num))

        return max([t.find_common(str(num)) for num in arr2])
