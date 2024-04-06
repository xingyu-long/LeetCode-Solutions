from math import inf
from typing import List


class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        arr = sorted(nums)
        n = len(arr)
        res, diff = inf, inf
        for i in range(n):
            left, right = i + 1, n - 1
            while left < right:
                total = arr[i] + arr[left] + arr[right]
                if abs(total - target) < diff:
                    diff = abs(total - target)
                    res = total
                if total > target:
                    right -= 1
                else:
                    left += 1
        return res
