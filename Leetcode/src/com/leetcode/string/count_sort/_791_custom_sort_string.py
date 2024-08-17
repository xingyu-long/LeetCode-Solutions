from collections import Counter


class Solution:
    def customSortString(self, order: str, s: str) -> str:
        # counting sort
        counter = Counter(s)
        res = []
        for ch in order:
            if ch in counter:
                res.append(ch * counter[ch])
                counter[ch] = 0

        for ch in counter:
            if counter[ch] > 0:
                res.append(ch * counter[ch])

        return "".join(res)
