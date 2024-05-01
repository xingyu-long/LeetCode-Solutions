class Solution:
    # time: n^2
    def checkValidString(self, s: str) -> bool:
        memo = {}

        def dfs(i, left):
            if left < 0:
                return False
            if i == len(s):
                return left == 0
            if (i, left) in memo:
                return memo[(i, left)]

            res = False
            if s[i] == "(":
                res = dfs(i + 1, left + 1)
            elif s[i] == ")":
                res = dfs(i + 1, left - 1)
            else:
                res = dfs(i + 1, left + 1) or dfs(i + 1, left - 1) or dfs(i + 1, left)

            memo[(i, left)] = res
            return res

        return dfs(0, 0)


class Solution2:
    def checkValidString(self, s: str) -> bool:
        # 计算左括号可能存在的情况
        # https://leetcode.com/problems/valid-parenthesis-string/discuss/543521/Java-Count-Open-Parenthesis-O(n)-time-O(1)-space-Picture-Explain
        left_min, left_max = 0, 0
        for c in s:
            if c == "(":
                left_min += 1
                left_max += 1
            elif c == ")":
                left_min -= 1
                left_max -= 1
            else:
                # use * as (
                left_max += 1
                # use * as )
                left_min -= 1
            if left_max < 0:
                return False
            left_min = max(left_min, 0)
        return left_min == 0
