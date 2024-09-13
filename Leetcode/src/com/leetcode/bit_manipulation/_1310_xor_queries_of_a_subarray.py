from typing import List


class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        n = len(arr)
        prefix = [0] * (n + 1)
        for i in range(1, n + 1):
            prefix[i] = arr[i - 1] ^ prefix[i - 1]

        res = [0] * len(queries)
        for i in range(len(queries)):
            s, e = queries[i]
            res[i] = prefix[s] ^ prefix[e + 1]

        return res
