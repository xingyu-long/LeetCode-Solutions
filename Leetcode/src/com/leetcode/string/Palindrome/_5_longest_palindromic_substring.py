class Solution:
    # time: O(n^2)
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        dp = [[False] * n for _ in range(n)]
        res = ""
        for j in range(n):
            # i can be equal to j
            for i in range(j + 1):
                dp[i][j] = (s[i] == s[j]) and (j - i <= 1 or dp[i + 1][j - 1])
                if dp[i][j]:
                    if j - i + 1 > len(res):
                        res = s[i : j + 1]
        return res
