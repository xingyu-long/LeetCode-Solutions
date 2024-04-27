from typing import List


class Solution:
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
        arr = [0] * n
        res = 0

        def find_insert_pos(arr, target, l, r):
            while l + 1 < r:
                mid = l + (r - l) // 2
                if arr[mid] > target:
                    r = mid
                else:
                    l = mid
            if target <= arr[l]:
                return l
            if target > arr[r]:
                return r + 1
            return r

        for index, num in enumerate(nums):
            if res > 0:
                if num > arr[res - 1]:
                    arr[res] = num
                    res += 1
                else:
                    # find the place and insert
                    correct_idx = find_insert_pos(arr, num, 0, res - 1)
                    arr[correct_idx] = num
            else:
                arr[res] = num
                res += 1

        return res
