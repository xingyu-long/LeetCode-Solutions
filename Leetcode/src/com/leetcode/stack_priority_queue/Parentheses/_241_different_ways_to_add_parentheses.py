from typing import List


class Solution:
    # time: O(N * 2^N)
    def diffWaysToCompute(self, expression: str) -> List[int]:
        # backtracking
        res = []
        for i in range(len(expression)):
            ch = expression[i]
            if ch in "+-*":
                left = self.diffWaysToCompute(expression[0:i])
                right = self.diffWaysToCompute(expression[i + 1 :])
                for l in left:
                    for r in right:
                        if ch == "-":
                            res.append(l - r)
                        elif ch == "+":
                            res.append(l + r)
                        elif ch == "*":
                            res.append(l * r)

        if len(res) == 0:
            # it means no operation at all, just convert it to int.
            res.append(int(expression))

        return res
