from typing import List


class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:

        def find_first():
            left, right = 0, len(nums) - 1
            while left + 1 < right:
                mid = left + (right - left) // 2
                if nums[mid] >= target:
                    right = mid
                else:
                    left = mid
            if nums[left] == target:
                return left
            if nums[right] == target:
                return right
            return -1

        def find_last():
            left, right = 0, len(nums) - 1
            while left + 1 < right:
                mid = left + (right - left) // 2
                if nums[mid] <= target:
                    left = mid
                else:
                    right = mid
            if nums[right] == target:
                return right
            if nums[left] == target:
                return left
            return -1

        if not nums:
            return [-1, -1]
        return [find_first(), find_last()]
