from typing import List


class Solution:
    # time: O(N), N = len(differences)
    # space: O(0)
    def numberOfArrays(self, differences: List[int], lower: int, upper: int) -> int:
        """
        If upper - lower < max - min, no possible hidden sequences.
        If upper - lower == max - min, we have only 1 possible hidden sequences.
        If upper - lower == max - min + 1, we have 2 possible hidden sequences.
        If upper - lower == max - min + k, we have k + 1 possible hidden sequences.
        """
        curr = max_val = min_val = 0
        for diff in differences:
            curr += diff
            min_val = min(min_val, curr)
            max_val = max(max_val, curr)
        return max(0, (upper - lower) - (max_val - min_val) + 1)
