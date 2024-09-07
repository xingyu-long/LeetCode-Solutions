from typing import List


class Solution:
    def missingRolls(self, rolls: List[int], mean: int, n: int) -> List[int]:
        m = len(rolls)
        total = mean * (m + n)
        missing_total = total - sum(rolls)
        if missing_total < n or missing_total > 6 * n:
            return []

        avg = missing_total // n
        remain = missing_total % n
        res = [avg for _ in range(n)]

        for i in range(remain):
            res[i] += 1

        return res
