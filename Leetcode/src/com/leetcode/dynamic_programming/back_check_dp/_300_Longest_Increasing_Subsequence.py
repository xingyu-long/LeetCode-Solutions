from typing import List


class Solution:
    # time: O(n^2)
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        res = 0
        for i in range(n):
            best = 1
            for j in range(i + 1):
                if nums[j] < nums[i]:
                    best = max(best, dp[j] + 1)
            dp[i] = best
            res = max(res, dp[i])
        return res


class Solution2:
    # time: O(nlogn)
    # space: O(n)
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        sorted_array = [0] * n
        res = 0

        def find_insert_pos(array, target, left, right):
            while left + 1 < right:
                mid = left + (right - left) // 2
                if array[mid] > target:
                    right = mid
                else:
                    left = mid
            if target <= array[left]:
                return left
            if target > array[right]:
                return right + 1
            return right

        for _, num in enumerate(nums):
            if res > 0:
                if num > sorted_array[res - 1]:
                    sorted_array[res] = num
                    res += 1
                else:
                    # find the place and insert
                    correct_idx = find_insert_pos(sorted_array, num, 0, res - 1)
                    sorted_array[correct_idx] = num
            else:
                sorted_array[res] = num
                res += 1

        return res
