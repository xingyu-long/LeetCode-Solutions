from collections import Counter
from typing import List


class Solution:
    def countInterestingSubarrays(self, nums: List[int], modulo: int, k: int) -> int:
        """
        prefix sum
        if nums[i] % modulo == k  -> 1

        [3, 1, 9, 6]
        ||
        ||-->> sums = [1, 1, 2, 3]

        for range from l to r
        we'd like to know how many num meet the requirement: sums[r] - sums[l-1]

        (sums[r] - sums[l-1]) % modulo = k % modulo

        if we remove modulo, we will get (sums[r] - sums[l-1]) = k
        and for certain sum[r], we can get sums[r] - k = sums[l - 1]
        so, if there exists an index l such that l <= r, also satisfies (sums[r] - k) % mod
        and nums[l..r] would be an interesting subarray
        """
        n = len(nums)
        d = Counter({0: 1})
        res = 0
        prefix = 0
        for i in range(n):
            prefix += 1 if (nums[i] % modulo == k) else 0
            res += d[(prefix - k + modulo) % modulo]
            # same as 974. Subarray Sums Divisible by K for how to count it
            d[prefix % modulo] += 1
        return res
