from typing import List


class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        stack = []
        for item in pushed:
            stack.append(item)
            while stack and stack[-1] == popped[j]:
                stack.pop()
                j += 1
        return len(stack) == 0
