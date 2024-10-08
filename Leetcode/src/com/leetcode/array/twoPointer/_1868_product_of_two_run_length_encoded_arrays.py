from typing import List


class Solution:
    def findRLEArray(
        self, encoded1: List[List[int]], encoded2: List[List[int]]
    ) -> List[List[int]]:
        i, j = 0, 0
        m, n = len(encoded1), len(encoded2)
        res = []
        prods = []
        # two pointer
        while i < m and j < n:
            e1_val, e1_freq = encoded1[i]
            e2_val, e2_freq = encoded2[j]

            prod = e1_val * e2_val
            length = min(e1_freq, e2_freq)
            prods.append([prod, length])
            encoded1[i][1] -= length
            encoded2[j][1] -= length
            if encoded1[i][1] == 0:
                i += 1
            if encoded2[j][1] == 0:
                j += 1
            # 这一部分比较难想到
            if not res or res[-1][0] != prod:
                res.append([prod, length])
            else:
                res[-1][1] += length

        return res
