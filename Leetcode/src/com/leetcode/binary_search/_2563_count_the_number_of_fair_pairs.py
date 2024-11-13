from typing import List


class Solution:
    """
    Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6

    sorted: [0, 1, 4, 4, 5, 7]

    i = 0:
        - find_lower_bound(3 - 0) => index = 2
        - find_upper_bound(6 - 0) => index = 4
    i = 1:
        - find_lower_bound(3 - 1) => index = 2
        - find_upper_bound(6 - 1) => index = 4
    i = 2:
        - find_lower_bound(3 - 4) => index = -1
        - find_upper_bound(6 - 4) => index = -1
    ...
    """

    # time: O(NlogN)
    def countFairPairs(self, nums: List[int], lower: int, upper: int) -> int:
        def find_lower_bound(arr, left, right, target):
            # find the index which is the first number is greater than target
            while left + 1 < right:
                mid = left + (right - left) // 2
                if arr[mid] >= target:
                    right = mid
                else:
                    left = mid
            if arr[left] >= target:
                return left
            if arr[right] >= target:
                return right
            return -1

        def find_upper_bound(arr, left, right, target):
            while left + 1 < right:
                mid = left + (right - left) // 2
                if arr[mid] <= target:
                    left = mid
                else:
                    right = mid
            if arr[right] <= target:
                return right
            if arr[left] <= target:
                return left
            return -1

        arr = sorted(nums)
        n = len(arr)
        res = 0
        for i in range(n - 1):
            first = find_lower_bound(arr, i + 1, n - 1, lower - arr[i])
            last = find_upper_bound(arr, i + 1, n - 1, upper - arr[i])
            if first == -1 or last == -1:
                continue
            res += last - first + 1
        return res
