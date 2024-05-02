from collections import deque
from typing import List

from leetcode.common.py_utils import NestedInteger


class Solution:
    def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
        queue = deque()
        for l in nestedList:
            queue.append(l)
        res = 0
        prev = 0
        while queue:
            size = len(queue)
            res += prev
            for _ in range(size):
                level_sum = 0
                curr = queue.popleft()
                if curr.isInteger():
                    res += curr.getInteger()
                    level_sum += curr.getInteger()
                else:
                    for l in curr.getList():
                        queue.append(l)

                # cache this level sum
                prev += level_sum

        return res
