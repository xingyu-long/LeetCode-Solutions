'''
Date: 12/15/2020 10:07:48
LastEditTime: 12/15/2020 10:09:00
Description: Two pointer
'''


class Solution:
    # Time: O(N) space: O(N)
    def sortedSquares(self, nums: List[int]) -> List[int]:
        if not nums:
            return None
        left, right = 0, len(nums) - 1
        res = [0 for _ in range(len(nums))]
        index = right
        while left <= right:
            if abs(nums[left]) > abs(nums[right]):
                res[index] = nums[left]**2
                left += 1
            else:
                res[index] = nums[right]**2
                right -= 1
            index -= 1
        return res
