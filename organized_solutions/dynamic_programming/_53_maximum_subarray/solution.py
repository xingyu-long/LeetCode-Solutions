from math import inf
from typing import List


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        """
         idx 0    1    2   3   4    5   6   7    8
             -2,  1,  -3,  4,  -1,  2,  1,  -5,  4
        c_sum -2  1   -2   4   3    5   6   1    5
        res   -2  1    1   4   4    5   6   6    6
        """
        res = float(-inf)
        curr_sum = 0
        for num in nums:
            # 以当前num结尾的sub array，所有有两个选择，一个是加入之前的sub array 或者是新起一个sub array
            curr_sum = max(num, curr_sum + num)
            res = max(res, curr_sum)
        return res
