from collections import Counter


class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        c = Counter(s)
        for ch in t:
            c[ch] -= 1
            if c[ch] < 0:
                return False

        return False if any([value != 0 for value in c.values()]) else True
