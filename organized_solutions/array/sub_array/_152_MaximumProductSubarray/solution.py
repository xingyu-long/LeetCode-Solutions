from typing import List


class Solution:
    def maxProduct(self, nums: List[int]) -> int:

        n = len(nums)
        min_arr = [0] * n
        max_arr = [0] * n
        min_arr[0] = nums[0]
        max_arr[0] = nums[0]
        res = nums[0]
        for i in range(1, n):
            max_arr[i] = max(
                nums[i], max_arr[i - 1] * nums[i], min_arr[i - 1] * nums[i]
            )
            min_arr[i] = min(
                nums[i], max_arr[i - 1] * nums[i], min_arr[i - 1] * nums[i]
            )
            res = max(res, max_arr[i])

        return res
