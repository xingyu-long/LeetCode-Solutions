from collections import defaultdict, deque
from typing import List


class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        d = defaultdict(lambda: -1)
        stack = deque()
        res = []
        for idx, t in enumerate(temperatures):
            while stack and t > temperatures[stack[-1]]:
                d[stack.pop()] = idx
            stack.append(idx)
        for i in range(len(temperatures)):
            if i in d:
                res.append(d[i] - i)
            else:
                res.append(0)
        return res
            