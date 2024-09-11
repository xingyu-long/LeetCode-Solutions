from collections import defaultdict
from math import inf
from typing import List


class WordDistance:

    def __init__(self, wordsDict: List[str]):
        self.map = defaultdict(list)

        for i, word in enumerate(wordsDict):
            self.map[word].append(i)

    def shortest(self, word1: str, word2: str) -> int:
        seq1 = self.map[word1]
        seq2 = self.map[word2]
        i, j = 0, 0
        res = inf
        while i < len(seq1) and j < len(seq2):
            res = min(res, abs(seq1[i] - seq2[j]))
            if seq1[i] > seq2[j]:
                j += 1
            else:
                i += 1
        return res
