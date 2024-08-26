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


# this is NOT an answer for above question
# instead, this is UNION of intervals
def intervalUnion(A: List[List[int]], B: List[List[int]]) -> List[List[int]]:
    m, n = len(A), len(B)
    i = j = 0
    res = []
    while i < m or j < n:
        if i == m:
            curr = B[j]
            j += 1
        elif j == n:
            curr = A[i]
            i += 1
        elif A[i][0] < B[j][0]:
            curr = A[i]
            i += 1
        else:
            curr = B[j]
            j += 1

        if res and res[-1][-1] >= curr[0]:
            res[-1][-1] = max(res[-1][-1], curr[1])
        else:
            res.append(curr)

    print(res)
    return res


assert intervalUnion([[1, 5], [10, 14], [16, 18]], [[2, 6], [8, 10], [11, 20]]) == [
    [1, 6],
    [8, 20],
]
