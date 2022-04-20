'''
Date: 04/19/2022 20:24:51
LastEditTime: 04/19/2022 20:24:51
Description: In-order, BST
'''
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def recoverTree(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        prev = first = second = None

        def dfs(root):
            if not root:
                return
            nonlocal prev, first, second
            dfs(root.left)
            if prev and prev.val > root.val:
                if not first:
                    first = prev
                second = root
            prev = root
            dfs(root.right)
        dfs(root)
        first.val, second.val = second.val, first.val
        return root
