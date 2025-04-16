from typing import List

from common.py_utils import FenwickTree


class Solution:
    # time: O(NLogN)
    # worse case, O(N^2) since we need to insert the list
    # space: O(N)
    def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:

        def binary_search(arr: List[int], target: int) -> int:
            """
            find the index to insert target
            """
            if len(arr) == 0:
                return 0

            left, right = 0, len(arr) - 1
            while left + 1 < right:
                mid = left + (right - left) // 2
                if arr[mid] >= target:
                    right = mid
                else:
                    left = mid
            if target <= arr[left]:
                return left
            elif target > arr[right]:
                return right + 1
            else:
                return right

        idxes = {}
        for i, v in enumerate(nums1):
            idxes[v] = i

        n = len(nums2)
        res = 0
        sorted_list = []
        for num in nums2:
            # the index of num in num1
            idx = idxes[num]
            left = binary_search(sorted_list, idx)
            potential_right = n - 1 - idx
            processed = len(sorted_list) - left
            right = potential_right - processed
            res += left * right
            sorted_list.insert(left, idx)
        return res


class Solution2:
    # time: O(NLogN)
    # space: O(N)
    def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        idxes = {}
        for i, v in enumerate(nums1):
            idxes[v] = i

        n = len(nums2)
        res = 0
        fw = FenwickTree(n)
        for num in nums2:
            idx = idxes[num]
            left = fw.query(idx + 1)
            right = (n - 1 - idx) - (fw.query(n) - left)
            res += left * right
            fw.update(idx + 1, 1)
        return res
