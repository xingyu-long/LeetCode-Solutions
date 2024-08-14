from collections import Counter


class Solution:
    def longestPalindrome(self, s: str) -> int:
        counter = Counter(s)
        odd = 0
        res = 0
        for _, v in counter.items():
            if v % 2 == 0:
                res += v
            else:
                res += v - 1
                odd = True

        return res + (1 if odd else 0)
