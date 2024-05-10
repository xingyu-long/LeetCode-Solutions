class Solution:
    def calculate(self, s: str) -> int:
        if not s:
            return 0

        sign = "+"
        num = 0
        curr_res = 0
        res = 0
        n = len(s)

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
                    curr_res += num
                elif sign == "-":
                    curr_res -= num

                if ch == "+" or ch == "-" or i == n - 1:
                    res += curr_res
                    curr_res = 0

                sign = ch
                num = 0

            i += 1
        return res
