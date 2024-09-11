from collections import Counter


class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        counter = Counter(s)
        res = 0
        for v in counter.values():
            if v % 2 != 0:
                res += 1
                if res > 1:
                    return False
        return res <= 1
