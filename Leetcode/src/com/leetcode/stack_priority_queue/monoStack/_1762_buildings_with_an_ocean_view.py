from typing import List


class Solution:
    # time: O(n)
    # space: O(n)
    def findBuildings(self, heights: List[int]) -> List[int]:
        # monotonic stack (decreasing)
        stack = []
        for i in range(len(heights)):
            while stack and heights[i] >= heights[stack[-1]]:
                stack.pop()
            stack.append(i)
        return stack
