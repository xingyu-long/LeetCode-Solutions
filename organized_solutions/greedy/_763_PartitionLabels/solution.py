'''
Date: 11/12/2020 15:29:19
LastEditTime: 03/31/2022 17:59:42
Description: HashMap(dict)
'''
from typing import List


class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        n = len(s)
        # Use HashMap to store the latest index for each char.
        right_most = {ch:index for index, ch in enumerate(s)}

        start, max_pos = 0, 0
        res = []
        for i in range(n):
            max_pos = max(max_pos, right_most[s[i]])
            if i == max_pos:
                res.append(i - start + 1)
                start = i + 1
        return res