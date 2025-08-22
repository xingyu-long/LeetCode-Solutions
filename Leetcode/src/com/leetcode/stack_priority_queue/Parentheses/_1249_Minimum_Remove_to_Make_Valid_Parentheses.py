class Solution:
    def minRemoveToMakeValid(self, s: str) -> str:
        stack = []
        mismatch = set()
        for i in range(len(s)):
            if s[i] == "(":
                stack.append(i)
            elif s[i] == ")":
                if len(stack):
                    stack.pop()
                else:
                    mismatch.add(i)
        for item in list(stack):
            mismatch.add(item)
        res = []
        for i in range(len(s)):
            if i not in mismatch:
                res.append(s[i])
        return "".join(res)
