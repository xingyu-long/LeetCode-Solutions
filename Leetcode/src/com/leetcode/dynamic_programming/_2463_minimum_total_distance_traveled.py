from typing import List


class Solution:
    def minimumTotalDistance(self, robot: List[int], factory: List[List[int]]) -> int:
        robot.sort()
        factory.sort(key=lambda x: x[0])
        factory_pos = []
        for pos, quota in factory:
            factory_pos.extend([pos] * quota)

        def dfs(i, j, robot, pos, memo):
            if i == len(robot):
                return 0
            if j == len(pos):
                return 10**16

            if (i, j) in memo:
                return memo[(i, j)]

            # use the j_th pos to repair i_th robot
            pick = abs(robot[i] - pos[j]) + dfs(i + 1, j + 1, robot, pos, memo)
            # skip the j_th pos and try next one
            skip = dfs(i, j + 1, robot, pos, memo)
            memo[(i, j)] = min(pick, skip)
            return memo[(i, j)]

        return dfs(0, 0, robot, factory_pos, {})
