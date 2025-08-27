import bisect
import math
from typing import List


class Solution:
    # time: O(MlogN * logC)
    # M: len(nums1)
    # N: len(nums2)
    # C: -10**10-1, 10**10+1
    def kthSmallestProduct(self, nums1: List[int], nums2: List[int], k: int) -> int:
        """
        nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6

        1) x = -4
            x * y <= target -> y >= 6/(-4) (since the x is negative) -> use bisect_left, count += 2 - 0
        2) x = 0
            x * y <= target -> so any y would work, count += 2
        3) x = 3
            x * y <= target -> y <= target / x -> y <= 2 -> use bisect, count += 1
        """

        def count_less_eq(target):
            # x * y <= target
            count = 0
            for num in nums1:
                if num > 0:
                    count += bisect.bisect(nums2, target // num)
                elif num < 0:
                    count += len(nums2) - bisect.bisect_left(
                        nums2, math.ceil(target / num)
                    )
                elif target >= 0:
                    # it would be invalid if target < 0 with num = 0
                    count += len(nums2)

            return count

        left, right = -(10**10) - 1, 10**10 + 1

        while left + 1 < right:
            mid = left + (right - left) // 2
            if count_less_eq(mid) >= k:
                right = mid
            else:
                left = mid

        if count_less_eq(left) >= k:
            return left
        if count_less_eq(right) >= k:
            return right

        return -1
