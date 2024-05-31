from collections import defaultdict
from math import inf
from typing import List


class Solution:
    def minTransfers(self, transactions: List[List[int]]) -> int:

        def build_debt(transactions) -> List[int]:
            m = defaultdict(int)
            for t in transactions:
                u, v, w = t
                m[u] -= w
                m[v] += w
            debt = []
            for k, v in m.items():
                if v != 0:
                    debt.append(v)
            return debt

        def helper(curId: int, debt: List[int]) -> int:
            while curId < len(debt) and debt[curId] == 0:
                curId += 1
            if curId == len(debt):
                return 0

            # try different cases: settle debt[curId] and debt[i]
            min_transfer = inf
            for i in range(curId + 1, len(debt)):
                if debt[curId] * debt[i] < 0:
                    debt[i] += debt[curId]
                    min_transfer = min(min_transfer, helper(curId + 1, debt) + 1)
                    debt[i] -= debt[curId]
            return min_transfer

        debt = build_debt(transactions)
        return helper(0, debt)
