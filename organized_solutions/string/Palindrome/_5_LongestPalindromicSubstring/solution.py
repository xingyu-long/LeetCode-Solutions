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


class Solution2:
    # 扩散法
    # time: O(n)
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        res = ""

        def verify(i, j):
            nonlocal res
            while i >= 0 and j < n and s[i] == s[j]:
                i -= 1
                j += 1
            curr = s[i + 1 : j]
            if len(curr) > len(res):
                res = curr

        for i in range(n):
            verify(i, i)
            verify(i, i + 1)

        return res
