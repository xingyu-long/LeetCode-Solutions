class Solution:
    # time: O(m * n)
    # space: O(m * n)
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        """
           "" a c e
        ""  0 0 0 0
        a   0 1 1 1
        b   0 1 1 1
        c   0 1 2 2
        d   0 1 2 2
        e   0 1 2 3
        """
        m, n = len(text1), len(text2)
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                c1 = text1[i - 1]
                c2 = text2[j - 1]
                if c1 == c2:
                    dp[i][j] = dp[i - 1][j - 1] + 1
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
        return dp[m][n]
