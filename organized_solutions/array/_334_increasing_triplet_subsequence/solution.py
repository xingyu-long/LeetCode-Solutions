from math import inf
from typing import List


class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        # two pass
        # forward and backward array
        n = len(nums)
        # forward: min between [0, idx]
        forward = [0 for _ in range(n)]
        # backward: max between [idx, n-1]
        backward = [0 for _ in range(n)]
        forward[0] = nums[0]
        backward[-1] = nums[-1]
        for i in range(1, n):
            forward[i] = min(forward[i - 1], nums[i])
        for i in range(n - 2, -1, -1):
            backward[i] = max(backward[i + 1], nums[i])
        for i in range(n):
            if nums[i] > forward[i] and nums[i] < backward[i]:
                return True
        return False


class Solution2:
    def increasingTriplet(self, nums: List[int]) -> bool:
        first = second = inf
        for num in nums:
            if num <= first:
                first = num
            elif num <= second:
                second = num
            else:
                # we hit the third one
                return True
        return False
