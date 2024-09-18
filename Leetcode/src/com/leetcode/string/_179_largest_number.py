from functools import cmp_to_key
from typing import List


class Solution:
    def largestNumber(self, nums: List[int]) -> str:

        def comp(a: int, b: int):
            if a + b > b + a:
                return 1
            elif a == b:
                return 0
            else:
                return -1

        nums = [str(num) for num in nums]
        nums.sort(key=cmp_to_key(comp), reverse=True)
        # remove leading 0s, if empty, return "0"
        return "".join(nums).lstrip("0") or "0"
