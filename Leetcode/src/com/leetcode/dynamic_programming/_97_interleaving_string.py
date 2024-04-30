class Solution:
    # time: O(m * n)
    # space: O(m * n)
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for i in range(1, m + 1):
            if s1[i - 1] == s3[i - 1] and dp[i - 1][0]:
                dp[i][0] = True

        for j in range(1, n + 1):
            if s2[j - 1] == s3[j - 1] and dp[0][j - 1]:
                dp[0][j] = True

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                idx = i + j - 1
                # s1[i-1]和s2[j-1]可能相同应该都去验证其可能性
                if s1[i - 1] == s3[idx]:
                    dp[i][j] = dp[i][j] or dp[i - 1][j]
                if s2[j - 1] == s3[idx]:
                    dp[i][j] = dp[i][j] or dp[i][j - 1]

        print(f"dp={dp}")
        return dp[m][n]
