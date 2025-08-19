from collections import defaultdict
from typing import List


class Solution:
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[List[str]]:
        word_set = set(wordList)
        if endWord not in word_set:
            return []

        def neighbors(word):
            for i in range(len(word)):
                for j in range(26):
                    ch = chr(ord("a") + j)
                    new_word = word[:i] + ch + word[i + 1 :]
                    if new_word in word_set:
                        yield new_word

        level = dict()
        level[beginWord] = [[beginWord]]
        while level:
            next_level = defaultdict(list)
            for word, paths in level.items():
                if word == endWord:
                    return paths
                for neighbor in neighbors(word):
                    for path in paths:
                        next_level[neighbor].append(path + [neighbor])

            word_set -= set(next_level.keys())
            level = next_level

        return []
