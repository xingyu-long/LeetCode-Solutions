from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1:
            return nums[0]

        def rob_with_range(i, j):
            rob = not_rob = 0
            for k in range(i, j):
                temp = max(rob, not_rob)
                rob = not_rob + nums[k]
                not_rob = temp
            return max(rob, not_rob)

        return max(rob_with_range(0, n - 1), rob_with_range(1, n))
