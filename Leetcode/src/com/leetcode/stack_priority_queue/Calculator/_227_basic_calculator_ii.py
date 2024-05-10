class Solution:
    def calculate(self, s: str) -> int:
        n = len(s)
        curr_res = 0
        res = 0
        num = 0
        sign = "+"
        for i in range(n):
            ch = s[i]
            if ch.isdigit():
                num = num * 10 + int(ch)

            if i == n - 1 or ch in ("+", "-", "*", "/"):
                if sign == "+":
                    curr_res += num
                elif sign == "-":
                    curr_res -= num
                elif sign == "*":
                    curr_res *= num
                elif sign == "/":
                    # -3//2 -> -2
                    curr_res = int(curr_res / num)

                if i == n - 1 or ch in ("+", "-"):
                    res += curr_res
                    curr_res = 0

                sign = ch
                num = 0

        return res
