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


class Solution2:
    # without stack
    def calculate(self, s: str) -> int:
        sign = "+"
        num = 0
        idx = 0
        n = len(s)
        curr_res = 0
        res = 0
        while idx < n:
            if s[idx].isdigit():
                num = num * 10 + int(s[idx])

            if s[idx] in "+-*/" or idx == n - 1:
                if sign == "+":
                    curr_res += num
                elif sign == "-":
                    curr_res -= num
                elif sign == "*":
                    curr_res = curr_res * num
                elif sign == "/":
                    curr_res = int(curr_res / num)

                # 1+2*3, so we will record 1 into res
                if s[idx] in "+-" or idx == n - 1:
                    res += curr_res
                    curr_res = 0

                sign = s[idx]
                num = 0
            idx += 1

        return res
