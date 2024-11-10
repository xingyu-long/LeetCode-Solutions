class Solution:
    # time: O(N) -> TLE
    def minEnd(self, n: int, x: int) -> int:
        # bit manipulation
        """
        n = 3, x = 4
        3 => 011
        4 => 100  <----- so we can only start from x, so bit wise AND won't be 0 (if we take x = 3)
        5 => 101

        1) start from x, so bitwise AND will keep same bit. e.g., 100
            1.1) if we start from 3 (bit format: 011) and bitwise AND won't be 100 in any case.
        2) increment by 1 and bitwise OR with x, so the bits we want won't disappear
        """
        curr = x
        c = n
        while c > 1:
            curr = (curr + 1) | x
            c -= 1
        return curr


class Solution2:
    def minEnd(self, n: int, x: int) -> int:
        curr = x
        count = n - 1
        mask = 1

        while count > 0:
            # it means x in mask bit is zero
            if (mask & x) == 0:
                # fill this bit to get number which slightly bigger than curr
                curr |= (count & 1) * mask
                count >>= 1

            mask <<= 1

        return curr
