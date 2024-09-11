from random import randint
from typing import List


class Solution:
    """
    nums=[1,2,3,4,5]

    i=4, random pick: 1/5, assume pick i=2
    [1,2,5,4,3]
    i=3, random pick: 4/5 (missed) * 1/4 => 1/5, assume pick i=0
    [4,2,5,1,3]
    3rd, 4/5 (missed 1st) * 3/4 (missed 2nd) * 1/3 => 1/5
    ... and so on

    so everyone gets same probability which is 1/n
    """

    def __init__(self, nums: List[int]):
        self.nums = nums

    def reset(self) -> List[int]:
        return self.nums

    def shuffle(self) -> List[int]:
        clone = self.nums.copy()
        size = len(clone)
        for i in range(size)[::-1]:
            # Fisher–Yates shuffle
            r_idx = randint(0, i)
            clone[i], clone[r_idx] = clone[r_idx], clone[i]

        return clone
