from typing import List


class Solution:
    def minExtraChar(self, s: str, dictionary: List[str]) -> int:

        word_sets = set(dictionary)
        memo = dict()

        def dfs(index):
            if index == len(s):
                return 0
            if index in memo:
                return memo[index]

            res = float("inf")
            for i in range(index, len(s)):
                curr = s[index : i + 1]
                if curr in word_sets:
                    res = min(res, dfs(i + 1))
                else:
                    res = min(res, len(curr) + dfs(i + 1))
            memo[index] = res
            return res

        return dfs(0)
