# Definition for a Node.
from typing import Optional

from leetcode.common.py_utils import TreeNode as Node


class Solution:
    def treeToDoublyList(self, root: "Optional[Node]") -> "Optional[Node]":
        if not root:
            return None

        # in-order traversal
        curr = root
        stack = []
        prev = None
        first, last = None, None
        while stack or curr:
            while curr:
                stack.append(curr)
                curr = curr.left

            curr = stack.pop()
            if first is None:
                first = curr
            if prev:
                curr.left = prev
                prev.right = curr

            prev = curr
            last = curr
            curr = curr.right

        first.left = last
        last.right = first
        return first
