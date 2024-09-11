from collections import Counter
from math import inf


class Solution:
    def minWindow(self, s: str, t: str) -> str:
        counter = Counter(t)
        n, size = len(s), len(t)
        min_len = inf
        res = None
        start, end = 0, 0
        while end < n:
            counter[s[end]] -= 1
            if counter[s[end]] >= 0:
                size -= 1
            while size == 0:
                # shrink the size
                if end - start + 1 < min_len:
                    min_len = end - start + 1
                    res = s[start : end + 1]
                counter[s[start]] += 1
                if counter[s[start]] > 0:
                    size += 1
                start += 1
            end += 1

        return res if res else ""
