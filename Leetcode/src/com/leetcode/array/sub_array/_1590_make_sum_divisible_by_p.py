class Solution:
    # similar to "560. Subarray Sum Equals K"
    def minSubarray(self, nums: List[int], p: int) -> int:
        target = sum(nums) % p
        if target == 0:
            return 0

        n = len(nums)
        res = n
        total = 0
        m = {0: -1}
        # looking for shortest subarray where the sum of them % p == target
        for i, num in enumerate(nums):
            total += num
            total %= p
            key = (total - target) % p
            if key in m:
                res = min(res, i - m[key])
            m[total] = i

        return res if res < n else -1
