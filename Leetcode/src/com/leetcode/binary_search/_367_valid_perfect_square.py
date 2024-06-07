class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        left, right = 0, num
        while left + 1 < right:
            mid = left + (right - left) // 2
            if mid > num / mid:
                right = mid
            else:
                left = mid
        if left * left == num:
            return True
        if right * right == num:
            return True
        return False
