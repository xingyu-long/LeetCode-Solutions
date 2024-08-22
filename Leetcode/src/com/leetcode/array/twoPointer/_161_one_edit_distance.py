class Solution:
    def isOneEditDistance(self, s: str, t: str) -> bool:
        m, n = len(s), len(t)
        for i in range(min(m, n)):
            if s[i] != t[i]:
                if m == n:
                    # only 1 char difference
                    return s[i + 1 :] == t[i + 1 :]
                elif m > n:
                    # delete one char from s
                    return s[i + 1 :] == t[i:]
                else:
                    # add one char on s
                    return s[i:] == t[i + 1 :]
        return abs(m - n) == 1
