from collections import defaultdict


class Solution:
    # time: O(n)
    # space: O(1)
    def findTheLongestSubstring(self, s: str) -> int:
        vowels = {
            "a": 1,  # 00001
            "e": 2,  # 00010
            "i": 4,  # 00100
            "o": 8,  # 01000
            "u": 16,  # 10000
        }
        m = defaultdict(int)
        m[0] = -1
        res = state = 0
        for i in range(len(s)):
            if s[i] in vowels:
                # use XOR to represent odd or even
                state ^= vowels[s[i]]
            # only record the first index with such state (so the distance will be far)
            if state not in m:
                m[state] = i
            res = max(res, i - m[state])

        return res
