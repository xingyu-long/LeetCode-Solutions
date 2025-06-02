from typing import List


class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        for token in tokens:
            if token in ["+", "-", "*", "/"]:
                val_2 = stack.pop()
                val_1 = stack.pop()
                if token == "+":
                    stack.append(val_1 + val_2)
                elif token == "-":
                    stack.append(val_1 - val_2)
                elif token == "*":
                    stack.append(val_1 * val_2)
                elif token == "/":
                    # we cannot just use // since it won't
                    # handle the case where val_2 is negative.
                    stack.append(int(val_1 / val_2))
                else:
                    return -1
            else:
                stack.append(int(token))
        return stack[-1]
