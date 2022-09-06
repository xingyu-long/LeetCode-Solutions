'''
Date: 09/04/2022 10:04:42
LastEditTime: 09/04/2022 10:06:52
Description: Sort, Tree
'''
from collections import defaultdict, deque
from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def verticalTraversal(self, root: Optional[TreeNode]) -> List[List[int]]:
        queue = deque()
        queue.append((root, 0))
        d = defaultdict(list)
        while queue:
            size = len(queue)
            temp = defaultdict(list)
            for _ in range(size):
                curr, col = queue.popleft()
                temp[col].append(curr.val)
                if curr.left:
                    queue.append((curr.left, col - 1))
                if curr.right:
                    queue.append((curr.right, col + 1))

            #  sort elements where its in same col and same row (row is by level traversal)
            for key in temp:
                d[key] += sorted(temp[key])

        # sort by key
        return [d[key] for key in sorted(d)]
