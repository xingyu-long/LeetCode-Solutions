from typing import List


class Solution:
    def shiftingLetters(self, s: str, shifts: List[List[int]]) -> str:
        n = len(s)
        prefix = [0] * (n + 1)

        # mark the start and end as we did in sweep line
        for start, end, d in shifts:
            prefix[start] += 1 if d == 1 else -1
            # we restore the value from end + 1
            prefix[end + 1] += -1 if d == 1 else 1

        # build the real prefix sum array
        for i in range(1, n + 1):
            prefix[i] += prefix[i - 1]

        res = []
        for i in range(n):
            diff = ord(s[i]) - ord("a")
            diff += prefix[i]
            diff %= 26
            ch = chr(ord("a") + diff)
            res.append(ch)

        return "".join(res)
