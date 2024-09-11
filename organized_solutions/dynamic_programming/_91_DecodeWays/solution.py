class Solution:
    # time: O(n)
    def numDecodings(self, s: str) -> int:

        memo = {}

        def dfs(index, memo):
            if index == len(s):
                return 1
            if index in memo:
                return memo[index]

            res = 0
            single_digit = s[index]
            if 1 <= int(single_digit) < 10:
                res += dfs(index + 1, memo)
            if index + 1 < len(s):
                two_digits = s[index : index + 2]
                if 10 <= int(two_digits) <= 26:
                    res += dfs(index + 2, memo)

            memo[index] = res
            return res

        return dfs(0, memo)
