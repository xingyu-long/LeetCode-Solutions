from typing import List


class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        count = [0] * 1001
        for num in arr1:
            count[num] += 1
        res = []
        for num in arr2:
            if count[num] > 0:
                res.extend([num] * count[num])
                count[num] = 0
        for i in range(len(count)):
            if count[i] > 0:
                res.extend([i] * count[i])
                count[i] = 0
        return res
