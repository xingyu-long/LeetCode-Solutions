from typing import List


class Solution:
    def subarraysDivByK(self, nums: List[int], k: int) -> int:
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
