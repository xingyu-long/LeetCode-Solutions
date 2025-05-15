from typing import List


class Solution:
    def getWordsInLongestSubsequence(
        self, words: List[str], groups: List[int]
    ) -> List[str]:
        # find the Longest Subsequence and then go backwards
        # similar to LIS problem
        n = len(groups)
        dp = [0] * n
        prev = [-1] * n

        def check(a, b):
            if len(a) != len(b):
                return -1
            count = 0
            for i in range(len(a)):
                if a[i] != b[i]:
                    count += 1
            return count

        max_dp = 0
        max_dp_index = 0
        for j in range(n):
            # from i -> j
            dp[j] = 1
            for i in range(j):
                if groups[i] != groups[j] and check(words[i], words[j]) == 1:
                    if dp[i] + 1 > dp[j]:
                        dp[j] = dp[i] + 1
                        prev[j] = i
            if dp[j] > max_dp:
                max_dp = dp[j]
                max_dp_index = j

        res = []
        while max_dp_index != -1:
            res.append(words[max_dp_index])
            max_dp_index = prev[max_dp_index]
        return res[::-1]
