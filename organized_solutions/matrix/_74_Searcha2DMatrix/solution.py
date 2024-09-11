from typing import List


class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        rows, cols = len(matrix), len(matrix[0])
        left, right = 0, rows * cols - 1
        while left + 1 < right:
            mid = left + (right - left) // 2
            x, y = mid // cols, mid % cols
            if matrix[x][y] == target:
                return True
            elif matrix[x][y] > target:
                right = mid
            else:
                left = mid

        if matrix[left // cols][left % cols] == target:
            return True
        if matrix[right // cols][right % cols] == target:
            return True
        return False
