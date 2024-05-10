from random import randrange
from typing import List


class Solution:
    """
    [1, 2, 3, 3, 3]

    1st 3: 1 * (1/2) * (2/3) = 1/3
    2nd 3: (1/2) * (2/3) = 1/3
    3nd 3: 1/3
    """

    def __init__(self, nums: List[int]):
        self.nums = nums

    def pick(self, target: int) -> int:
        res = None
        count = 0
        for i, num in enumerate(self.nums):
            if num == target:
                count += 1
                # [0, count)
                random = randrange(0, count)
                if random == 0:
                    res = i
        return res
