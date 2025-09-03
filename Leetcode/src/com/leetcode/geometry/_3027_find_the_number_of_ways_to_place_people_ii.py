from math import inf
from typing import List


class Solution:
    # time: O(n^2)
    # space: O(1)
    def numberOfPairs(self, points: List[List[int]]) -> int:
        """
        y
        .
        .  p1
        .                p5
        .
        .      p2 p3
        .            p4
        .      p6
        ....................> x

        sort by x and then sort revesely on y
            p1, p2, p6, p3, p4, p5
        """
        n = len(points)
        points.sort(key=lambda p: (p[0], -p[1]))
        res = 0
        for i in range(n):
            bottom_right_y = -inf
            for j in range(i + 1, n):
                if points[i][1] >= points[j][1] > bottom_right_y:
                    res += 1
                    # the smaller this is, we would have a bigger rectange to form with points[i]
                    # so, any point's y is smaller than this `bottom_right_y` would be included in the rectangle
                    # for example, p1 and p2 makes a rectangle, so when we do p1 and p6, above condition won't meet
                    # since p6'y is smaller than previous bottom_right_y, i.e. p2's y.
                    bottom_right_y = points[j][1]

        return res
