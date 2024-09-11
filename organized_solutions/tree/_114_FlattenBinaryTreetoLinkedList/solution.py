from typing import Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    # iterative
    # time: O(n)
    # space: O(n)
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        if not root:
            return
        stack = []
        stack.append(root)
        while stack:
            curr = stack.pop()
            if curr.right:
                stack.append(curr.right)
            if curr.left:
                stack.append(curr.left)
            if stack:
                curr.right = stack[-1]
            curr.left = None


class Solution:
    # recursion
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        if not root:
            return

        self.flatten(root.left)
        self.flatten(root.right)

        if not root.left:
            # it has been moved
            return
        left = root.left
        while left.right:
            left = left.right

        left.right = root.right
        root.right = root.left
        root.left = None
