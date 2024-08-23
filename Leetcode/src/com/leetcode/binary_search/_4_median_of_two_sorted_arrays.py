from typing import List


class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        len1, len2 = len(nums1), len(nums2)
        if len2 < len1:
            return self.findMedianSortedArrays(nums2, nums1)

        left, right = 0, len1
        while left <= right:
            partition_x = (left + right) // 2
            # make sure partition_x has 1 more if total number of elements are odd
            partition_y = (len1 + len2 + 1) // 2 - partition_x

            """
                    p_x
            x1, x2, x3 x4.... xn
                    p_y
            y1, y2, y3 y4.... yn

            x2 <= y3 and y2 <= x3
            """
            # max of left partition for array X
            max_left_x = float("-inf") if partition_x == 0 else nums1[partition_x - 1]
            # min of right partition for array X
            min_right_x = float("inf") if partition_x == len1 else nums1[partition_x]

            max_left_y = float("-inf") if partition_y == 0 else nums2[partition_y - 1]
            min_right_y = float("inf") if partition_y == len2 else nums2[partition_y]

            if max_left_x <= min_right_y and max_left_y <= min_right_x:
                if (len1 + len2) % 2 == 0:
                    return (
                        (max(max_left_x, max_left_y) + min(min_right_x, min_right_y))
                        / 2
                        * 1.0
                    )
                else:
                    return max(max_left_x, max_left_y)
            elif max_left_x > min_right_y:
                right = partition_x - 1
            else:
                left = partition_x + 1

        return -1.0
