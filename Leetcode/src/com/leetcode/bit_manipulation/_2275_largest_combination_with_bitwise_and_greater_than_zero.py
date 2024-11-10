from typing import List


class Solution:
    # time: O(32 * N)
    # space: O(1)
    def largestCombination(self, candidates: List[int]) -> int:
        # we only care bitwise AND is greater than 0
        res = 0
        for i in range(32):
            # go over each num for this particular bit position
            count = sum(num & (1 << i) > 0 for num in candidates)
            res = max(res, count)

        return res


class Solution2:
    def largestCombination(self, candidates: List[int]) -> int:
        return max(sum(num & (1 << i) > 0 for num in candidates) for i in range(32))
