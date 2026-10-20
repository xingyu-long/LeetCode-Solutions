from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []

        def dfs(s, path):
            if len(path) == len(nums):
                res.append(list(path))
                return

            for i in range(len(nums)):
                if nums[i] not in s:
                    s.add(nums[i])
                    path.append(nums[i])
                    dfs(s, path)
                    path.pop()
                    s.remove(nums[i])

        dfs(set(), [])
        return res
