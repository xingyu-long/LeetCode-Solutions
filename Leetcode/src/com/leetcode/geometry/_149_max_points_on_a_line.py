from typing import List
from collections import defaultdict


class Solution:
    def maxPoints(self, points: List[List[int]]) -> int:
        def gcd(a, b):
            if a == 0:
                return b
            return gcd(b % a, a)

        def get_slope(p1: List[int], p2: List[int]):
            dx = p2[0] - p1[0]
            dy = p2[1] - p1[1]
            if dx == 0:
                return f"0-{p1[1]}"
            if dy == 0:
                return f"{p1[0]}-0"

            d = gcd(dx, dy)
            return f"{dx/d}-{dy/d}"

        n = len(points)
        res = 0
        for i in range(n):
            m = defaultdict(int)
            p1 = points[i]
            same = 1
            max_points = 0
            for j in range(i + 1, n):
                p2 = points[j]
                if p1[0] == p2[0] and p1[1] == p2[1]:
                    same += 1
                else:
                    slope = get_slope(p1, p2)
                    m[slope] += 1
                    max_points = max(max_points, m[slope])
            res = max(res, same + max_points)

        return res
