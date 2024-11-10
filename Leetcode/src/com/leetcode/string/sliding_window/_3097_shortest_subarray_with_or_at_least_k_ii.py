class Solution:
    def update(self, bits, x, change):
        # insert or remove x depends on "change"
        for i in range(32):
            if (x >> i) & 1:
                bits[i] += change

    def bits_to_num(self, bits):
        res = 0
        for i in range(32):
            if bits[i]:
                res |= 1 << i
        return res

    # time: O(N)
    # space: O(32) -> O(1)
    def minimumSubarrayLength(self, nums: List[int], k: int) -> int:
        # sliding window? -> the way to remove the start index from the window
        bits = [0] * 32
        res = float("inf")
        n = len(nums)
        start, end = 0, 0
        while end < n:
            # add this to the window
            self.update(bits, nums[end], 1)
            while start <= end and self.bits_to_num(bits) >= k:
                res = min(res, end - start + 1)
                # remove nums[start] from the window
                self.update(bits, nums[start], -1)
                start += 1

            end += 1

        return -1 if res == float("inf") else res
