'''
Date: 04/19/2022 19:54:09
LastEditTime: 04/19/2022 19:55:27
Description: In-order traversal
'''
from collections import deque
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class BSTIterator:
    def __init__(self, root: Optional[TreeNode]):
        self.deque = deque()
        self.curr = root

    def next(self) -> int:
        res = None

        while self.curr != None:
            self.deque.append(self.curr)
            self.curr = self.curr.left
        self.curr = self.deque.pop()
        res = self.curr.val
        self.curr = self.curr.right

        return res

    def hasNext(self) -> bool:
        return len(self.deque) > 0 or self.curr != None
