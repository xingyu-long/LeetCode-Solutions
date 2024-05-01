from typing import List


class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        points.sort(key=lambda x: x[0])
        # similar to merge intervals
        res, n = 0, len(points)
        _, end = points[0]
        for i in range(1, n):
            # [1,2], [2,3] 如果我们打2可以弄破，所以这里用 "<="
            if points[i][0] <= end:
                # 这里取的是最小，因为你没办法去链接，而是在当前可以merge的情况下弄破这个气球
                end = min(end, points[i][1])
            else:
                res += 1
                _, end = points[i]
        # for the last [start, end]
        res += 1
        return res
