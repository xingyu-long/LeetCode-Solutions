from typing import List


class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:

        L = R = 0
        for ch in s:
            if ch == "(":
                L += 1
            elif ch == ")":
                if L > 0:
                    L -= 1
                else:
                    R += 1

        res = set()

        def dfs(idx, left, right, count, path):
            if left < 0 or right < 0 or count < 0:
                return

            if idx == len(s):
                if left == 0 and right == 0 and count == 0:
                    res.add("".join(path))
                return

            ch = s[idx]
            if ch == "(":
                # skip this "("
                dfs(idx + 1, left - 1, right, count, path)
                # do not skip this "(" for now
                dfs(idx + 1, left, right, count + 1, path + [ch])
            elif ch == ")":
                # skip this ")"
                dfs(idx + 1, left, right - 1, count, path)
                # do not skip this ")" for now
                dfs(idx + 1, left, right, count - 1, path + [ch])
            else:
                # add it into path
                dfs(idx + 1, left, right, count, path + [ch])

        dfs(0, L, R, 0, [])
        return list(res)
