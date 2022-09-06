'''
Date: 09/05/2022 13:48:46
LastEditTime: 09/05/2022 13:48:46
Description: Reorder, Anagrams
'''
from collections import defaultdict
from typing import List


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            count = [0] * 26
            for c in s:
                count[ord(c) - ord('a')] += 1
            d[tuple(count)].append(s)
        return d.values()
