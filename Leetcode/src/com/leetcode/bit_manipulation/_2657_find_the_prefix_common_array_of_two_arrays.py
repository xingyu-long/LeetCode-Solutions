from typing import List


class Solution:
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        res = []
        seen = curr = 0
        for chs in zip(A, B):
            for ch in chs:
                # it means we saw this before
                if (1 << ch) & seen > 0:
                    curr += 1
                seen |= 1 << ch
            res.append(curr)
        return res
