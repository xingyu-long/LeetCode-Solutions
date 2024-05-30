from collections import deque
from typing import List

from leetcode.common.py_utils import NestedInteger


class Solution:
    def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
        res = 0
        queue = deque()
        for item in nestedList:
            queue.append(item)

        prev = 0
        while queue:
            size = len(queue)
            level_total = prev
            for _ in range(size):
                curr = queue.popleft()
                if curr.isInteger():
                    level_total += curr.getInteger()
                else:
                    for item in curr.getList():
                        queue.append(item)

            res += level_total
            prev = level_total

        return res
