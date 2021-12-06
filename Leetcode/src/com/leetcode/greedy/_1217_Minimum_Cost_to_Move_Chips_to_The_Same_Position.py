'''
Date: 12/05/2021 19:39:43
LastEditTime: 12/05/2021 19:41:07
Description: Greedy
'''
from typing import List


class Solution:
    def minCostToMoveChips(self, position: List[int]) -> int:
        # 总是能移动到1或者2的位置。
        odd, even = 0, 0
        for p in position:
            if p % 2 == 0:
                even += 1
            else:
                odd += 1
        return min(even, odd)

    def minCostToMoveChips2(self, position: List[int]) -> int:
        odds = sum(x % 2 for x in position)
        return min(odds, len(position) - odds)
