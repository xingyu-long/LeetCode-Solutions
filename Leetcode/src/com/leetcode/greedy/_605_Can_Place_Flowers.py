'''
Date: 12/05/2020 11:35:36
LastEditTime: 12/05/2020 11:35:48
Description: Greedy
'''


class Solution:
    def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
        length = len(flowerbed)
        for i in range(length):
            if flowerbed[i] == 1:
                continue
            prev = 0 if i - 1 < 0 else flowerbed[i - 1]
            next = 0 if i + 1 >= length else flowerbed[i + 1]
            if prev == 0 and next == 0:
                n -= 1
                flowerbed[i] = 1
        return n <= 0
