from typing import List


class Solution:
    def partition(self, s: str) -> List[List[str]]:
        res = []

        def is_palindrome(s):
            left, right = 0, len(s) - 1
            while left < right:
                if s[left] != s[right]:
                    return False
                left += 1
                right -= 1
            return True

        def dfs(path, index):
            if index == len(s):
                res.append(list(path))
                return

            for i in range(index, len(s)):
                if is_palindrome(s[index : i + 1]):
                    path.append(s[index : i + 1])
                    dfs(path, i + 1)
                    path.pop()

        dfs([], 0)
        return res


class Solution2:
    def partition(self, s: str) -> List[List[str]]:
        res = []
        dp = None

        def build_palindrome():
            n = len(s)
            dp = [[False] * n for _ in range(n)]
            for i in range(n):
                for j in range(i + 1):
                    if s[i] == s[j] and (i - j <= 2 or dp[j + 1][i - 1]):
                        dp[j][i] = True
            return dp

        def dfs(path, index):
            nonlocal dp

            if index == len(s):
                res.append(list(path))
                return

            for i in range(index, len(s)):
                if dp[index][i]:
                    path.append(s[index : i + 1])
                    dfs(path, i + 1)
                    path.pop()

        dp = build_palindrome()
        dfs([], 0)
        return res


class Solution3:
    def partition(self, s: str) -> List[List[str]]:
        # dp + word break II?
        n = len(s)
        dp = [[False] * n for _ in range(n)]
        for i in range(n):
            dp[i][i] = True
            for j in range(i + 1):
                dp[j][i] = s[j] == s[i] and (i - j <= 2 or dp[j + 1][i - 1])

        def dfs(idx):
            if idx == n:
                # use it as place holder, so the upper layer call
                # still go through `for nxt in next_words` code.
                return [""]

            res = []
            for j in range(idx, n):
                if dp[idx][j]:
                    curr = s[idx : j + 1]
                    next_words = dfs(j + 1)
                    for nxt in next_words:
                        temp = [curr]
                        temp.extend(nxt)
                        res.append(list(temp))
            return res

        return dfs(0)
