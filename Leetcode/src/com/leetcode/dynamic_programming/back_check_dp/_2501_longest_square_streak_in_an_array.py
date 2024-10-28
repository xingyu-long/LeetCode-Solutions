from typing import List


class Solution:
    # time: O(NlogN)
    # space: O(N)
    def longestSquareStreak(self, nums: List[int]) -> int:
        dp = defaultdict(int)
        res = 0
        for num in sorted(nums):
            root = num**0.5
            if root**2 == num:
                dp[num] = dp[root] + 1
            else:
                dp[num] = 1
            res = max(res, dp[num])

        return -1 if res < 2 else res
