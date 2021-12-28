'''
Date: 12/28/2021 14:49:18
LastEditTime: 12/28/2021 14:49:19
Description: Bit
'''


class Solution:
    def findComplement(self, num: int) -> int:
        res = 0
        count = 0
        while num > 0:
            last = num & 1
            num = num >> 1
            res += (2 ** count) * (1 - last)
            count += 1
        return res
