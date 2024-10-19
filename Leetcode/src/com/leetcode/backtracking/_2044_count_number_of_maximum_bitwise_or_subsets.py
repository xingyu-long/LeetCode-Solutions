from typing import List


class Solution:
    # time: O(2^N)
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        target = 0
        for num in nums:
            target |= num

        def dfs(idx, curr):
            res = 0
            if curr == target:
                # we may have same curr, so we just add 1 into res,
                # instead of return it right away.
                res += 1

            for i in range(idx, len(nums)):
                res += dfs(i + 1, curr | nums[i])

            return res

        return dfs(0, 0)
