class Solution:
    # time: O(n^2)
    def isValidPalindrome(self, s: str, k: int) -> bool:

        memo = dict()
        n = len(s)

        # get maximum length of palindrome subsequence
        def dfs(i, j):
            if i > j:
                return 0
            if i == j:
                return 1
            if (i, j) in memo:
                return memo[(i, j)]

            res = 0
            if s[i] == s[j]:
                res = dfs(i + 1, j - 1) + 2
            else:
                res = max(res, dfs(i, j - 1), dfs(i + 1, j))
            memo[(i, j)] = res
            return res

        max_palindrome = dfs(0, n - 1)
        return n - max_palindrome <= k
