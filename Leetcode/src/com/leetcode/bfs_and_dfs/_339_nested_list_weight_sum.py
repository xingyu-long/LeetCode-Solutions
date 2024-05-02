from collections import deque
from typing import List

from leetcode.common.py_utils import NestedInteger


class Solution:
    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        # use BFS
        queue = deque()
        level, res = 0, 0
        for l in nestedList:
            queue.append(l)
        while queue:
            size = len(queue)
            level += 1
            for _ in range(size):
                curr = queue.popleft()
                if curr.isInteger():
                    res += curr.getInteger() * level
                else:
                    queue.extend(curr.getList())
        return res


class Solution2:
    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        res = 0

        def dfs(depth, curr_list):
            nonlocal res

            for curr in curr_list:
                if not curr.isInteger():
                    dfs(depth + 1, curr.getList())
                else:
                    res += curr.getInteger() * depth

        dfs(1, nestedList)
        return res
