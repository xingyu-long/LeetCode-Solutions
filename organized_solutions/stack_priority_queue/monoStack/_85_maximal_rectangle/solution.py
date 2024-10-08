from typing import List


class Solution:
    # time: O(rows * cols)
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        def calculate(heights:List[int]):
            stack = []
            res, n = 0, len(heights)
            for i in range(n + 1):
                h = 0 if i == n else heights[i]
                while stack and h < heights[stack[-1]]:
                    height = heights[stack.pop()]
                    start = stack[-1] if stack else -1
                    area = (i - start - 1) * height
                    res = max(res, area)
                stack.append(i)
            return res
        
        rows, cols = len(matrix), len(matrix[0])
        arr = [0] * cols
        res = 0
        for i in range(rows):
            for j in range(cols):
                if matrix[i][j] != "0":
                    arr[j] += int(matrix[i][j])
                else:
                    arr[j] = 0
            res = max(res, calculate(arr))
        return res
