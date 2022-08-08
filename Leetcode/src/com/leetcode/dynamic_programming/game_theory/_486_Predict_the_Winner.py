'''
Date: 12/12/2020 16:21:32
LastEditTime: 12/12/2020 16:21:47
Description: MinMax, Top-down + Memo
'''


class Solution:
    def PredictTheWinner(self, nums: List[int]) -> bool:
        if not nums:
            return False
        memo = [[-1 for _ in range(len(nums) + 1)]
                for _ in range(len(nums) + 1)]
        return self.dfs(nums, 0, len(nums) - 1, memo) >= 0

    def dfs(self, nums, left, right, memo):
        if left > right:
            return 0
        if memo[left][right] != -1:
            return memo[left][right]
        take_left = nums[left] - self.dfs(nums, left + 1, right, memo)
        take_right = nums[right] - self.dfs(nums, left, right - 1, memo)
        better = max(take_left, take_right)
        memo[left][right] = better
        return better
