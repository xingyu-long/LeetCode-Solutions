from typing import List


class Solution:
    def canSortArray(self, nums: List[int]) -> bool:
        """
        [33,17,8,4,2,3,5]  -->  [[33,17], [8,4,2], [3,5]]
        for each group, the num should be greater than prev_max
        """
        curr_bits = 0
        prev_max = 0
        curr_max = 0
        for num in nums:
            bits = num.bit_count()
            if bits != curr_bits:
                # start new group
                curr_bits = bits
                prev_max = curr_max

            if num < prev_max:
                return False
            curr_max = max(curr_max, num)

        return True
