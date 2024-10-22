class Solution:
    # time: O(N)
    # space: O(N)
    def parseBoolExpr(self, expression: str) -> bool:

        def dfs(s: str, start: int, end: int):
            ch = s[start]
            if end == start:
                return ch == "t"
            # why this matter? -> "&(|(f))"
            res = ch == "&"
            level, next_start = 0, start + 2
            for i in range(start + 2, end + 1):
                # how to split sub problems
                if level == 0 and s[i] in [",", ")"]:
                    curr = dfs(s, next_start, i - 1)
                    next_start = i + 1
                    if s[start] == "&":
                        res &= curr
                    elif s[start] == "|":
                        res |= curr
                    elif s[start] == "!":
                        res = not curr

                if s[i] == "(":
                    level += 1
                elif s[i] == ")":
                    level -= 1

            return res

        return dfs(expression, 0, len(expression) - 1)


class Solution2:
    def parseBoolExpr(self, expression: str) -> bool:
        stack = []
        for i in range(len(expression)):
            ch = expression[i]
            if ch == ")":
                # inner match
                seen = set()
                while stack[-1] != "(":
                    seen.add(stack.pop())
                stack.pop()  # pop out "("

                op = stack.pop()
                if op == "&":
                    stack.append("f" if "f" in seen else "t")
                elif op == "|":
                    stack.append("t" if "t" in seen else "f")
                else:
                    stack.append("f" if "t" in seen else "t")
            elif ch != ",":
                stack.append(ch)

        return stack[-1] == "t"
