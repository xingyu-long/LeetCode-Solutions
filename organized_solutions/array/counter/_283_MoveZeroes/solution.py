from typing import List


class Solution:
    # time: O(n + n -> n)
    # space: O(1)
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        # move non-zero ahead and fill the rest with zero
        if not nums:
            return None
        idx = 0
        for i in range(len(nums)):
            if nums[i] != 0:
                nums[idx] = nums[i]
                idx += 1
        for i in range(idx, len(nums)):
            nums[i] = 0
        return nums


class Solution2:
    # time: O(n)
    # space: O(1)
    # with less operation
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        # swap elements between no-zero and zero
        if not nums:
            return None
        i, j, n = 0, 0, len(nums)
        while i < n and j < n:
            if nums[i] != 0:
                nums[i], nums[j] = nums[j], nums[i]
                j += 1
            i += 1
        return nums
