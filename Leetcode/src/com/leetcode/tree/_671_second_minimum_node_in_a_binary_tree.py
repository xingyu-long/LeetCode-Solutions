from collections import deque
from math import inf
from typing import Optional

from common.py_utils import TreeNode


class Solution:
    def findSecondMinimumValue(self, root: Optional[TreeNode]) -> int:
        first = second = inf
        queue = deque()
        queue.append(root)
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                if curr.val < first:
                    first = curr.val
                else:
                    if first < curr.val < second:
                        second = curr.val
                        break

                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)

        return -1 if second == inf else second
