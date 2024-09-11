from typing import List


class Solution:
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        if not triplets:
            return False
        # O(n)
        c = 3
        res = [0] * c
        for t in triplets:
            # check all eligible ones
            if all([t[i] <= target[i] for i in range(c)]):
                for i in range(c):
                    res[i] = max(res[i], t[i])
        return res == target
