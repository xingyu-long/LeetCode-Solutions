from Leetcode.src.com.leetcode.common import TreeNode


class Solution:
    # 利用一样的思路，但可否用map优化？
    def isValid(self, s: str) -> bool:
        stack = []
        for ch in s:
            if ch == '(' or ch == '[' or ch == '{':
                stack.append(ch)
            else:
                # If stack is empty
                if not stack:
                    return False
                if stack[-1] == '(' and ch != ')':
                    return False
                if stack[-1] == '[' and ch != ']':
                    return False
                if stack[-1] == '{' and ch != '}':
                    return False
                stack.pop()

        return len(stack) == 0


class Solution_2:
    def isValid(self, s: str) -> bool:
        stack = []
        m = {']': '[', '}': '{', ')': '('}
        for ch in s:
            if ch in m.values():
                stack.append(ch)
            else:
                # If stack is empty or doesn't match.
                if not stack or stack[-1] != m[ch]:
                    return False
                stack.pop()

        return len(stack) == 0


solution = Solution()
print(solution.isValid('[]{}'))
