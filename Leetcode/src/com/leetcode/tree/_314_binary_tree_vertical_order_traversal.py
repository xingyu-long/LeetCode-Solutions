# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from collections import defaultdict, deque
from typing import List, Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    # use BFS to get range of index and then add corresponding list into result
    # time: O(n)
    # space: O(n)
    def verticalOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []

        d = defaultdict(list)
        queue = deque()
        queue.append((root, 0))
        left, right = 0, 0
        while queue:
            size = len(queue)
            for _ in range(size):
                curr, index = queue.popleft()
                d[index].append(curr.val)

                if curr.left:
                    left = min(left, index - 1)
                    queue.append((curr.left, index - 1))
                if curr.right:
                    right = max(right, index + 1)
                    queue.append((curr.right, index + 1))

        res = []
        for i in range(left, right + 1):
            res.append(d[i])
        return res
