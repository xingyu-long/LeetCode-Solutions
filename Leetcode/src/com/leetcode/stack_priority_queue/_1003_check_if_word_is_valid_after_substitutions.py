class Solution:
    # time: O(n)
    # space: O(n)
    # 类似于 (()) match的那个题
    def isValid(self, s: str) -> bool:
        stack = []
        for ch in s:
            if ch != "c":
                stack.append(ch)
            else:
                if len(stack) >= 2 and stack[-2] == "a" and stack[-1] == "b":
                    stack.pop()
                    stack.pop()
                else:
                    return False
        return len(stack) == 0
