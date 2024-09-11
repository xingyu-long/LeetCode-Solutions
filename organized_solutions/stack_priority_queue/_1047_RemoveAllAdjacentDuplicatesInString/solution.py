class Solution:
    def removeDuplicates(self, s: str) -> str:
        stack = []
        for ch in s:
            if stack:
                if ch == stack[-1]:
                    stack.pop()
                else:
                    stack.append(ch)
            else:
                stack.append(ch)
        return "".join(stack)
