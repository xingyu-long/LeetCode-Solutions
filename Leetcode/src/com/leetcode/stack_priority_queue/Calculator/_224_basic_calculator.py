class Solution:
    def calculate(self, s: str) -> int:
        sign = "+"
        num, n = 0, len(s)
        stack = []

        i = 0
        while i < n:
            ch = s[i]

            if ch.isdigit():
                num = num * 10 + int(ch)
            elif ch == "(":
                count = 0
                j = i
                while i < n:
                    if s[i] == "(":
                        count += 1
                    elif s[i] == ")":
                        count -= 1
                    if count == 0:
                        break
                    i += 1
                # anything between "(" number... ")"
                num = self.calculate(s[j + 1 : i])

            if ch == "+" or ch == "-" or i == n - 1:
                if sign == "+":
                    stack.append(num)
                elif sign == "-":
                    stack.append(-num)

                sign = ch
                num = 0

            i += 1
        return sum(stack)
