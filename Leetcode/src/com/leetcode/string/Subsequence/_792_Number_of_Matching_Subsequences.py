'''
Date: 08/08/2022 15:08:50
LastEditTime: 08/08/2022 15:11:47
Description: Trie, Binary Search
'''
from collections import defaultdict
from typing import List


class Solution:
    class Node:
        def __init__(self, word):
            self.word = word
            self.index = 0

    """
    https://leetcode.com/problems/number-of-matching-subsequences/discuss/1290406/C%2B%2BJavaPython-Process-by-bucket-Picture-explain-O(N-%2B-S)
    time: O(# of words * len(word) + len(s))
    space: O(# of words)
    """
    def numMatchingSubseq(self, s: str, words: List[str]) -> int:
        buckets = defaultdict(list)
        # {ch -> []}
        for word in words:
            start_char = word[0]
            buckets[start_char].append(self.Node(word))

        res = 0
        for ch in s:
            current_bucket = buckets[ch]
            # Empty the bucket for next iteration
            buckets[ch] = []
            for node in current_bucket:
                node.index += 1
                if node.index == len(node.word):
                    res += 1
                else:
                    start_char = node.word[node.index]
                    buckets[start_char].append(node)
        return res
