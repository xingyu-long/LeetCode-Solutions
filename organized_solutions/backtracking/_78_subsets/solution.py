from typing import List


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []

        def dfs(nums, path, i):
            # cannot reuse path since it may be modified later.
            res.append(list(path))

            for j in range(i, len(nums)):
                path.append(nums[j])
                dfs(nums, path, j + 1)
                path.pop()

        dfs(nums, [], 0)
        return res
