from typing import List


class Solution:
    def arraysIntersection(
        self, arr1: List[int], arr2: List[int], arr3: List[int]
    ) -> List[int]:
        p1 = p2 = p3 = 0
        res = []
        while p1 < len(arr1) and p2 < len(arr2) and p3 < len(arr3):
            curr_min = min(arr1[p1], arr2[p2], arr3[p3])
            if arr1[p1] == arr2[p2] == arr3[p3]:
                res.append(arr1[p1])
            if arr1[p1] == curr_min:
                p1 += 1
            if arr2[p2] == curr_min:
                p2 += 1
            if arr3[p3] == curr_min:
                p3 += 1
        return res
