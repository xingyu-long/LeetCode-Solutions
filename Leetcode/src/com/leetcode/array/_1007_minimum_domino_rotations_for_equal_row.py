from typing import List


class Solution:
    def minDominoRotations(self, tops: List[int], bottoms: List[int]) -> int:
        n = len(tops)

        def compare(A: List[int], B: List[int], target: int) -> int:
            a = b = 0

            for i in range(n):
                if A[i] != target and B[i] != target:
                    break
                if A[i] != target:
                    a += 1
                if B[i] != target:
                    b += 1

                if i == n - 1:
                    return min(a, b)

            return -1

        # we can just try to match either tops[0] or bottoms[0]
        for target in [tops[0], bottoms[0]]:
            ret = compare(tops, bottoms, target)
            if ret != -1:
                return ret
        return -1
