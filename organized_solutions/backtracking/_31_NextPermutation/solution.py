from typing import List


class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        if not nums:
            return None

        def reverse(nums, i, j):
            while i < j:
                nums[i], nums[j] = nums[j], nums[i]
                i += 1
                j -= 1

        n = len(nums)
        first_small = -1
        # 1. find the first number which nums[i] < nums[i+1] from end
        for i in range(n - 1)[::-1]:
            if nums[i] < nums[i + 1]:
                first_small = i
                break
        if first_small == -1:
            reverse(nums, 0, n - 1)
            return

        first_large = -1
        # 2. find the first number which nums[i] > nums[first_small] from end
        for i in range(first_small + 1, n)[::-1]:
            if nums[i] > nums[first_small]:
                first_large = i
                break
        # 3. swap num in both locations
        nums[first_small], nums[first_large] = nums[first_large], nums[first_small]
        # 4. reverse from first_small+1 to the end
        reverse(nums, first_small + 1, n - 1)
