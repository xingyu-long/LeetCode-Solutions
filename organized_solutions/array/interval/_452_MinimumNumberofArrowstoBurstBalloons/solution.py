from typing import List


class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        if not points:
            return 0
        points.sort(key=lambda x: x[0])
        res = 1
        end = points[0][1]
        for i in range(1, len(points)):
            # no intersection and we need 1 arrow.
            if points[i][0] > end:
                end = points[i][1]
                res += 1
            else:
                end = min(end, points[i][1])
        return res
