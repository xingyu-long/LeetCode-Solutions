class Solution:
    def minBitFlips(self, start: int, goal: int) -> int:
        res = 0
        for _ in range(32):
            # check last digit
            res += (start ^ goal) & 1
            start >>= 1
            goal >>= 1
        return res
