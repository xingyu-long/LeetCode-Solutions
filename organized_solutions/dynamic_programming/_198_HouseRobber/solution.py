from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        # [n][2]
        dp = [[0] * 2 for _ in range(n)]
        # rob it
        res = nums[0]
        dp[0][1] = nums[0]

        for i in range(1, n):
            # 1) don't rob
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
            # 2) rob it
            dp[i][1] = dp[i - 1][0] + nums[i]
            res = max(res, dp[i][0], dp[i][1])

        return res


class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        rob = nums[0]
        no = 0
        res = max(rob, no)

        for i in range(1, n):
            # 1) don't rob
            prevNo = no
            no = max(no, rob)
            # 2) rob it
            rob = prevNo + nums[i]
            res = max(res, no, rob)

        return res
