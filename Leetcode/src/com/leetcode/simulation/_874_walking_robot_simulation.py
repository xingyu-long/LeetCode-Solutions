from typing import List


class Solution:
    def robotSim(self, commands: List[int], obstacles: List[List[int]]) -> int:
        dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        obstacle_set = set()
        for obstacle in obstacles:
            obstacle_set.add((obstacle[0], obstacle[1]))

        res = 0
        curr_dir = 0
        x = y = 0
        for command in commands:
            if command == -2:
                curr_dir = (curr_dir + 3) % 4
            elif command == -1:
                curr_dir = (curr_dir + 1) % 4
            else:
                steps = command
                while (
                    steps > 0
                    and (x + dirs[curr_dir][0], y + dirs[curr_dir][1])
                    not in obstacle_set
                ):
                    x += dirs[curr_dir][0]
                    y += dirs[curr_dir][1]
                    steps -= 1
            res = max(res, x * x + y * y)
        return res
