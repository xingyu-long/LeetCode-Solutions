class Solution:
    def calculate(self, s: str) -> int:
        sign = "+"
        num = 0
        stack = []
        n = len(s)

        i = 0
        while i < n:
            ch = s[i]

            if ch.isdigit():
                num = num * 10 + int(ch)

            if ch in ("+", "-", "*", "/") or i == n - 1:
                if sign == "+":
                    stack.append(num)
                elif sign == "-":
                    stack.append(-num)
                elif sign == "*":
                    stack.append(stack.pop() * num)
                elif sign == "/":
                    stack.append(int(stack.pop() / num))

                sign = ch
                num = 0

            i += 1
        return sum(stack)