class Solution:
    # time: O(N^2)
    # space: O(N^2)
    def longestPalindromeSubseq(self, s: str) -> int:
        memo = {}

        def dfs(i, j):
            if i == j:
                return 1
            if i > j:
                return 0
            if (i, j) in memo:
                return memo[(i, j)]

            res = 0
            if s[i] == s[j]:
                res = dfs(i + 1, j - 1) + 2
            else:
                res = max(res, dfs(i + 1, j), dfs(i, j - 1))

            memo[(i, j)] = res
            return res

        return dfs(0, len(s) - 1)
