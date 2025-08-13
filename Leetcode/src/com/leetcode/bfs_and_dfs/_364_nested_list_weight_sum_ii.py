from collections import deque
from typing import List

from leetcode.common.py_utils import NestedInteger


class Solution:
    def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
        queue = deque()
        for item in nestedList:
            queue.append(item)

        res = 0
        level = 0
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                if curr.isInteger():
                    level += curr.getInteger()
                else:
                    for item in curr.getList():
                        queue.append(item)
            # we don't clean up the level sum since
            # it will be carried to the next level.
            res += level

        return res
