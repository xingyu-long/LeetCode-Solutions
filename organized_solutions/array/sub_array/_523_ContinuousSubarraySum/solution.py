from typing import List


class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        if not nums:
            return False

        total = 0
        # [23,2,4,6,6], k=7
        m = {0: -1}
        for i, num in enumerate(nums):
            total += num
            if total != 0:
                total %= k
            # find the prev sum which had same total after % k
            if total in m:
                if i - m[total] > 1:
                    return True
            else:
                m[total] = i
        return False
