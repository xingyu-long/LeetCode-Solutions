from collections import Counter


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        counter = Counter()
        start, end = 0, 0
        res = 0
        while end < len(s):
            counter[s[end]] -= 1
            while counter[s[end]] < -1:
                counter[s[start]] += 1
                start += 1
            res = max(res, end - start + 1)
            end += 1
        return res
