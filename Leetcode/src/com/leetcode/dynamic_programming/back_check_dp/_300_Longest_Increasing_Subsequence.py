'''
Date: 12/07/2020 16:06:36
LastEditTime: 12/07/2020 17:17:22
Description: DP / Binary Search
'''


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        dp = [0 for _ in range(len(nums))]
        res = 0
        for i in range(0, len(nums)):
            curr_max = 0
            for j in range(0, i):
                if nums[i] > nums[j]:
                    curr_max = max(curr_max, dp[j])
            dp[i] = curr_max + 1
            res = max(res, dp[i])
        return res


class Solution_2:
    def lengthOfLIS(self, nums: List[int]) -> int:
        sort_list = [0 for _ in range(len(nums))]
        res = 0
        sort_list[res] = nums[0]
        res += 1
        for num in nums:
            if num > sort_list[res - 1]:
                sort_list[res] = num
                res += 1
            else:
                idx = self.find(sort_list, num, 0, res - 1)
                sort_list[idx] = num
        return res

    def find(self, sort_list, target, left, right):
        while left + 1 < right:
            mid = left + (right - left) // 2
            if sort_list[mid] >= target:
                right = mid
            else:
                left = mid
        if target <= sort_list[left]:
            return left
        if target > sort_list[right]:
            return right + 1
        return right
