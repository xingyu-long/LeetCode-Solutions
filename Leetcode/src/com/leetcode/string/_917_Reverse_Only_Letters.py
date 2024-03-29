'''
Date: 09/14/2021 19:30:21
LastEditTime: 09/14/2021 19:30:21
Description: swap
'''


class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        l, r = 0, len(s) - 1
        chs = list(s)
        while l < r:
            while l < r and not chs[l].isalpha():
                l += 1
            while l < r and not chs[r].isalpha():
                r -= 1
            chs[l], chs[r] = chs[r], chs[l]
            l, r = l + 1, r - 1
        return ''.join(chs)
