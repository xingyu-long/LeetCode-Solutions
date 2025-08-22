from typing import List


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        nums.sort()

        visited = set()
        res = []
        n = len(nums)

        def backtracking(path):
            if len(path) == n:
                res.append(list(path))

            for i in range(n):
                if i in visited or (
                    i > 0 and i - 1 not in visited and nums[i] == nums[i - 1]
                ):
                    continue
                visited.add(i)
                path.append(nums[i])
                backtracking(path)
                path.pop()
                visited.remove(i)

        backtracking([])
        return res
