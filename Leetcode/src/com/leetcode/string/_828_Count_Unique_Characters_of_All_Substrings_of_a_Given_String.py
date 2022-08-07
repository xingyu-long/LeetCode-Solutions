'''
Date: 08/05/2022 17:22:08
LastEditTime: 08/05/2022 17:23:11
Description: String
'''
from collections import defaultdict
from string import ascii_uppercase


class Solution:
    def uniqueLetterString(self, s: str) -> int:
        d = defaultdict(list)
        for idx, ch in enumerate(s):
            d[ch].append(idx)
        for key in d:
            d[key] = [-1] + d[key] + [len(s)]
        res = 0
        for ch in ascii_uppercase:
            for idx in range(1, len(d[ch]) - 1):
                res += (d[ch][idx] - d[ch][idx - 1]) * \
                    (d[ch][idx + 1] - d[ch][idx])
        return res
