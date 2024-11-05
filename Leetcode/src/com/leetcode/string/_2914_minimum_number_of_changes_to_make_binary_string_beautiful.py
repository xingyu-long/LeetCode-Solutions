class Solution:
    # time: O(N)
    # space: O(N)
    def minChanges(self, s: str) -> int:
        n = len(s)
        res = 0
        # check each pair
        for i in range(0, n, 2):
            if s[i] != s[i + 1]:
                res += 1
        return res
