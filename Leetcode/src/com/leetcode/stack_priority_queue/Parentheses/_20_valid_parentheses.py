class Solution:
    # 利用一样的思路，但可否用map优化？
    def isValid(self, s: str) -> bool:
        stack = []
        for ch in s:
            if ch == "(" or ch == "[" or ch == "{":
                stack.append(ch)
            else:
                # If stack is empty
                if not stack:
                    return False
                if stack[-1] == "(" and ch != ")":
                    return False
                if stack[-1] == "[" and ch != "]":
                    return False
                if stack[-1] == "{" and ch != "}":
                    return False
                stack.pop()

        return len(stack) == 0


class Solution2:
    def isValid(self, s: str) -> bool:
        m = {")": "(", "}": "{", "]": "["}
        stack = []
        for ch in s:
            if ch in m.values():
                stack.append(ch)
            elif ch in m:
                if stack and m[ch] == stack[-1]:
                    stack.pop()
                else:
                    return False
            else:
                # unreachable
                return False
        return len(stack) == 0
