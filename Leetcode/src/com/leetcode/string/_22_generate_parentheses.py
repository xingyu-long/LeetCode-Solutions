from typing import List


class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        # backtracking
        res = []

        def backtracking(left: int, right: int, curr: str):
            if left > n or right > n:
                return

            if left == n and right == n:
                res.append(curr)

            backtracking(left + 1, right, curr + "(")
            if left > right:
                backtracking(left, right + 1, curr + ")")

        backtracking(0, 0, "")
        return res
