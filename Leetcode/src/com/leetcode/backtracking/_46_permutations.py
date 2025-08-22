from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []
        visited = set()
        n = len(nums)

        def backtracking(path):
            if len(path) == n:
                res.append(list(path))

            for i in range(n):
                if i not in visited:
                    visited.add(i)
                    path.append(nums[i])
                    backtracking(path)
                    path.pop()
                    visited.remove(i)

        backtracking([])
        return res
