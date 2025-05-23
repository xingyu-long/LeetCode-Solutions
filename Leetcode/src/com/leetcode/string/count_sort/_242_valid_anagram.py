from collections import Counter, defaultdict


class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        m = defaultdict(int)
        for ch in s:
            m[ch] += 1
        for ch in t:
            m[ch] -= 1

        return all([v == 0 for v in m.values()])
