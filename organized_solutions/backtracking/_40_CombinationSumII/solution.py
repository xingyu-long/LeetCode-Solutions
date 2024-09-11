from typing import List


class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        candidates.sort()
        res = []

        def dfs(path, target, index):
            if target == 0:
                res.append(list(path))
                return
            for i in range(index, len(candidates)):
                if i != index and candidates[i] == candidates[i - 1]:
                    continue
                if target - candidates[i] >= 0:
                    path.append(candidates[i])
                    dfs(path, target - candidates[i], i + 1)
                    path.pop()

        dfs([], target, 0)
        return res
