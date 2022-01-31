'''
Date: 01/30/2022 17:40:16
LastEditTime: 01/30/2022 17:40:16
Description: Math, Queue
'''


from ast import List
from collections import deque


class Solution:
    def sequentialDigits(self, low: int, high: int) -> List[int]:
        q = deque(range(1, 10))
        res = []
        while q:
            curr = q.popleft()
            if curr >= low and curr <= high:
                res.append(curr)
            last = curr % 10
            if last < 9:
                next_element = curr * 10 + last + 1
                q.append(next_element)
        return res
