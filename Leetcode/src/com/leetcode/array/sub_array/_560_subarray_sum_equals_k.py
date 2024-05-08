from typing import List


class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        # hash table?
        m = {0: 1}
        res = 0
        prefix = 0
        for num in nums:
            prefix += num
            if prefix - k in m:
                res += m[prefix - k]
            m[prefix] = m.get(prefix, 0) + 1
        return res
