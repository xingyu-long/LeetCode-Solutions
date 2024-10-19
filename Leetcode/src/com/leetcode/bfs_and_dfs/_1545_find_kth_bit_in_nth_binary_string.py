class Solution:
    # time: O(N)
    # space: O(N)
    def findKthBit(self, n: int, k: int) -> str:
        """
        S1 = "0"
        S2 = "011"
        S3 = "0111001"
        S4 = "011100110110001"

        k=11
        """
        if n == 1:
            return "0"

        # length of current string
        count = 2**n - 1
        if k - 1 == count // 2:
            return "1"
        if k - 1 < count // 2:
            # left half
            return self.findKthBit(n - 1, k)
        # right half
        return "1" if self.findKthBit(n - 1, count - k + 1) == "0" else "0"
