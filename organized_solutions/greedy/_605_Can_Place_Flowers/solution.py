from typing import List


class Solution:
    def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
        length = len(flowerbed)
        for i in range(length):
            if flowerbed[i] == 1:
                continue
            prev = 0 if i - 1 < 0 else flowerbed[i - 1]
            nxt = 0 if i + 1 >= length else flowerbed[i + 1]
            if prev == 0 and nxt == 0 and n > 0:
                n -= 1
                flowerbed[i] = 1
        return n == 0
