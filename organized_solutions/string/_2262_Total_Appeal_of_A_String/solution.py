'''
Date: 08/05/2022 16:49:04
LastEditTime: 08/05/2022 16:50:17
Description: String
'''
from collections import defaultdict


class Solution:
    def appealSum(self, s: str) -> int:
        if not s:
            return 0
        d = defaultdict(lambda: -1)
        res = 0
        for idx, ch in enumerate(s):
            res += (idx - d[ch]) * (len(s) - idx)
            d[ch] = idx
        return res
