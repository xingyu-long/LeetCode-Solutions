from typing import List


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        word_set = set(wordDict)
        memo = {}

        def dfs(index, memo):
            if index == len(s):
                return [""]
            if index == memo:
                return memo[index]

            res = []
            for j in range(index + 1, len(s) + 1):
                if s[index:j] in word_set:
                    next_words = dfs(j, memo)
                    for nxt in next_words:
                        curr = s[index:j]
                        if len(nxt) > 0:
                            curr += " " + nxt
                        res.append(curr)

            memo[index] = res
            return res

        return dfs(0, memo)
