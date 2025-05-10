from typing import List


class Solution:
    def minSum(self, nums1: List[int], nums2: List[int]) -> int:
        """
        zeros_1 = count1
        total_1
        total_1 + zeros_1 (numbers)

        zeros_2 = count2
        total_2 + zeros_2 (numbers)
        """
        zeros1 = zeros2 = 0
        total1 = total2 = 0
        for num in nums1:
            if num == 0:
                zeros1 += 1
            total1 += num
        for num in nums2:
            if num == 0:
                zeros2 += 1
            total2 += num

        if zeros1 == zeros2 == 0:
            return total1 if total1 == total2 else -1
        elif zeros1 == 0:
            return total1 if total2 + zeros2 <= total1 else -1
        elif zeros2 == 0:
            return total2 if total1 + zeros1 <= total2 else -1

        # check the smallest sum -> maximum of two potential arrays
        return max(zeros1 + total1, zeros2 + total2)
