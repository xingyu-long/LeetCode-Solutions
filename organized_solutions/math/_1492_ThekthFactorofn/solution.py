'''
Date: 12/04/2020 08:59:27
LastEditTime: 12/04/2020 09:00:34
Description: Math
'''


class Solution:
    # Cache the half of them.
    def kthFactor(self, n: int, k: int) -> int:
        factors = []
        for factor in range(1, int(math.sqrt(n)) + 1):
            if n % factor == 0:
                if factor * factor != n:
                    factors.append(factor)
                k -= 1
                if k == 0:
                    return factor
        size = len(factors)
        return -1 if k > size else n // factors[size - k]
