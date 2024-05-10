from random import randint
from typing import List


class Solution:
    """
    w = [1,2,3,4]
    sums = [1,3,6,10]
    [1], [2-3], [4-6], [7,10]
    """

    def __init__(self, w: List[int]):
        self.nums = [0] * len(w)
        self.nums[0] = w[0]
        for i in range(1, len(w)):
            self.nums[i] = self.nums[i - 1] + w[i]

    def pickIndex(self) -> int:
        n = len(self.nums)
        left, right = 0, n - 1
        # [1, nums[-1]]
        random = randint(1, self.nums[-1])
        # get the index of random by using binary search
        while left + 1 < right:
            mid = left + (right - left) // 2
            if self.nums[mid] >= random:
                right = mid
            else:
                left = mid

        if random > self.nums[right]:
            return right + 1
        if random <= self.nums[left]:
            return left
        return right
