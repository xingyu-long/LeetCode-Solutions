from collections import Counter
from typing import List


class Solution:
    def findEvenNumbers(self, digits: List[int]) -> List[int]:
        counter = Counter(digits)

        res = []
        k = 3

        def backtracking(index: int, curr: int) -> None:
            if index == k:
                res.append(curr)
                return

            for digit in range(10):
                if index == 0 and digit == 0:
                    continue
                if index == k - 1 and digit % 2 == 1:
                    continue
                if counter[digit] > 0:
                    counter[digit] -= 1
                    backtracking(index + 1, curr * 10 + digit)
                    counter[digit] += 1

        backtracking(0, 0)
        return res
