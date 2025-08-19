from typing import List


class Solution:
    # Time: O(logN)
    def search(self, nums: List[int], target: int) -> bool:
        left, right = 0, len(nums) - 1
        while left + 1 < right:
            mid = left + (right - left) // 2
            if nums[mid] < nums[right]:
                if nums[mid] <= target <= nums[right]:
                    left = mid
                else:
                    right = mid
            elif nums[mid] > nums[right]:
                if nums[left] <= target <= nums[mid]:
                    right = mid
                else:
                    left = mid
            else:
                right -= 1

        return nums[left] == target or nums[right] == target
