from typing import List


class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        ops = set("+-*/")
        stack = list()
        for token in tokens:
            if token in ops:
                val_2 = stack.pop()
                val_1 = stack.pop()
                if token == "+":
                    stack.append(val_1 + val_2)
                elif token == "-":
                    stack.append(val_1 - val_2)
                elif token == "*":
                    stack.append(val_1 * val_2)
                elif token == "/":
                    stack.append(int(val_1 / val_2))
                else:
                    return -1
            else:
                stack.append(int(token))
        return stack[-1]
