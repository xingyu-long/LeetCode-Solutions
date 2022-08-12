'''
Date: 08/10/2022 15:07:43
LastEditTime: 08/10/2022 15:07:44
Description: Prefix, Suffix
'''


class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        if not s:
            return 0
        n = len(s)
        convert_to_one = [0] * (n + 1)
        convert_to_zero = [0] * (n + 1)
        # [0, i)
        for i in range(1, n + 1):
            count = (0 if s[i - 1] == '0' else 1)
            convert_to_zero[i] = convert_to_zero[i - 1] + count
        # [i, n]
        for j in range(n - 1, -1, -1):
            count = (0 if s[j] == '1' else 1)
            convert_to_one[j] = convert_to_one[j + 1] + count
        res = float('inf')
        for x, y in zip(convert_to_zero, convert_to_one):
            res = min(res, x + y)
        return res
