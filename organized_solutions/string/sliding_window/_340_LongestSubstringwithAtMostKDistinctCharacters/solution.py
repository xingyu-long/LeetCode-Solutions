from collections import Counter


class Solution:
    def lengthOfLongestSubstringKDistinct(self, s: str, k: int) -> int:
        counter = Counter()
        start, end = 0, 0
        n = len(s)
        res = 0
        while end < n:
            if counter[s[end]] == 0:
                k -= 1
            counter[s[end]] -= 1

            while k < 0:
                counter[s[start]] += 1
                if counter[s[start]] == 0:
                    k += 1
                start += 1
            res = max(res, end - start + 1)
            end += 1
        return res
