class Solution:

    MOD = 10**9 + 7

    def countGoodNumbers(self, n: int) -> int:
        """
        even: 0, 2, 4, 6, 8

        prime: 2, 3, 5, 7

        n = 1
        0, 2, 4, 6, 8 => 5

        n = 2
        [0, 2, 4, 6, 8] x [2, 3, 5, 7] = 16

        n = 3
        [0, 2, 4, 6, 8] x [2, 3, 5, 7] x [0, 2, 4, 6, 8]
        """

        # if n % 2 == 0:
        #     return 4**(n//2) * 5**(n//2) % self.MOD
        # else:
        #     return 5**(n//2 + 1) * 4**(n//2) % self.MOD
        def power_n(base: int, n: int) -> int:
            if n == 0:
                return 1

            half = power_n(base, n // 2) % self.MOD
            if n % 2 == 0:
                return half * half
            else:
                return half * half * base

        return (power_n(4, n // 2) * power_n(5, n - n // 2)) % self.MOD
