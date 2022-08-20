'''
Date: 08/19/2022 11:01:29
LastEditTime: 08/19/2022 11:04:28
Description: Counting sort
'''
from collections import Counter


class Solution:
    # time: O(~(1+2+3...n) * logn)
    def reorganizeString(self, s: str) -> str:
        prev = None
        c = Counter(s)
        used = 0
        res = []
        while used != len(s):
            curr = prev
            for ch, _ in c.most_common():
                if not curr or (curr and curr != ch):
                    res.append(ch)
                    curr = ch
                    c[ch] -= 1
                    if c[ch] == 0:
                        del c[ch]
                    used += 1
                    break
    
            if prev == curr:
                return ""
            prev = curr
        return ''.join(res)
                