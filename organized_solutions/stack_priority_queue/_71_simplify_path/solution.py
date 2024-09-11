class Solution:
    def simplifyPath(self, path: str) -> str:
        # stack
        text = path.replace("//", "/")
        ops = text.split("/")
        stack = []
        for op in ops:
            if op == "..":
                if stack:
                    stack.pop()
            elif op != "." and op != "":
                stack.append(op)
        return "/" + "/".join(stack)
