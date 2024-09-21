from typing import List


class Solution:
    """
    从1开始尝试，每次看当前数乘以10的情况，如果是大于n
    则代表我们需要移除最后一位，然后加上1
    """

    def lexicalOrder(self, n: int) -> List[int]:
        res = [0] * n
        curr = 1
        for i in range(n):
            res[i] = curr
            curr *= 10
            while curr > n:
                curr //= 10
                curr += 1
                while curr % 10 == 0:
                    curr //= 10
        return res

