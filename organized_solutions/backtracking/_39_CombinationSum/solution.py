from typing import List


class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        res = []

        def dfs(target, index, path):
            if target == 0:
                res.append(list(path))
                return

            for i in range(index, len(candidates)):
                if target - candidates[i] >= 0:
                    path.append(candidates[i])
                    dfs(target - candidates[i], i, path)
                    path.pop()

        dfs(target, 0, [])
        return res
