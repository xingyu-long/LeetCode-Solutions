from collections import Counter
from typing import List


class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        m = Counter(changed)
        res = []
        for item in sorted(changed):
            if m[item] > 0:
                m[item] -= 1
                if m[2 * item] > 0:
                    m[2 * item] -= 1
                    res.append(item)
                else:
                    return []

        return res if len(res) == len(changed) / 2 else []