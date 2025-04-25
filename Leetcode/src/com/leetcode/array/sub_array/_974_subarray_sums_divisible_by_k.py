from typing import List


class Solution:
    def subarraysDivByK(self, nums: List[int], k: int) -> int:
        """
        for range from l to r
        sums[r] - sums[l-1] = n * k, and n is any integer.

        both % by k
        sum[r] % k  - sums[l-1] % k = (n * k) % k

        so we will get sums[r] % k = sums[r-1] % k
        it means as long as we have same prefix sum, we will be
        divisible by K.
        """
        total = 0
        m = {0: 1}
        res = 0
        for num in nums:
            total += num
            if total != 0:
                total %= k

            if total in m:
                res += m[total]

            m[total] = m.get(total, 0) + 1
        return res
