from typing import List


class Solution:
    def intervalIntersection(
        self, firstList: List[List[int]], secondList: List[List[int]]
    ) -> List[List[int]]:
        # two pointer + interval?
        i = j = 0
        m, n = len(firstList), len(secondList)

        def get_interval(a, b):
            return [max(a[0], b[0]), min(a[1], b[1])]

        def is_intersect(a, b):
            return a[1] >= b[0] and a[0] <= b[1]

        res = []
        while i < m and j < n:
            if is_intersect(firstList[i], secondList[j]):
                res.append(get_interval(firstList[i], secondList[j]))

            if firstList[i][1] > secondList[j][1]:
                j += 1
            else:
                i += 1
        return res
