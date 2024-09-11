from typing import List


class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        """
        solution 1: n^2 to go through each possible rectangle
        solution 2: stack?
        """
        stack = []
        res, n = 0, len(heights)
        for i in range(n + 1):
            h = 0 if i == n else heights[i]
            while stack and h < heights[stack[-1]]:
                height = heights[stack.pop()]
                start = -1 if not stack else stack[-1]
                area = height * (i - start - 1)
                res = max(res, area)
            stack.append(i)
        return res
