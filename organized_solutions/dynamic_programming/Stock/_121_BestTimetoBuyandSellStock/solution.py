from math import inf
from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        buy_min = inf
        res = 0
        for price in prices:
            buy_min = min(buy_min, price)
            res = max(res, price - buy_min)
        return res
