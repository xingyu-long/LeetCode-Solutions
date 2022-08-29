'''
Date: 08/28/2022 14:02:34
LastEditTime: 08/28/2022 14:02:35
Description: Tree, Evaluate
'''

# Definition for a binary tree node.
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def evaluateTree(self, root: Optional[TreeNode]) -> bool:
        if not root:
            return False

        if (not root.left) and (not root.right):
            return root.val == 1

        left = self.evaluateTree(root.left)
        right = self.evaluateTree(root.right)
        if root.val == 2:
            return left or right
        elif root.val == 3:
            return left and right
        return False
