from typing import List


class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        res = []
        nums.sort()
        def dfs(path, index):
            res.append(list(path))
            for i in range(index, len(nums)):
                if i != index and nums[i] == nums[i - 1]:
                    continue
                path.append(nums[i])
                dfs(path, i + 1)
                path.pop()
        
        dfs([], 0)
        return res