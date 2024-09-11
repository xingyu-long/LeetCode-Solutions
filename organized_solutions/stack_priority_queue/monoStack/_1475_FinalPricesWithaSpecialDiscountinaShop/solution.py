from typing import List


class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        res = prices.copy()
        stack = []
        for i, price in enumerate(prices):
            while stack and price <= prices[stack[-1]]:
                idx = stack.pop()
                res[idx] = prices[idx] - price

            stack.append(i)
        return res
