from typing import List


class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        # sliding window
        start = end = 0
        n = len(nums)
        count = 0
        total = 0
        res = 0
        while end < n:
            count += 1
            total += nums[end]
            while count * total >= k:
                count -= 1
                total -= nums[start]
                start += 1

            res += end - start + 1
            end += 1
        return res
