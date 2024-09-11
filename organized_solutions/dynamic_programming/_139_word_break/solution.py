from typing import List


class Solution:

    # time: O(n^2)
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        word_set = set(wordDict)
        memo = {}

        def dfs(index, memo):
            if index == len(s):
                return True
            if index in memo:
                return memo[index]

            for j in range(index + 1, len(s) + 1):
                if s[index:j] in word_set and dfs(j, memo):
                    memo[index] = True
                    return True

            memo[index] = False
            return False

        return dfs(0, memo)
