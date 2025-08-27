"""
Date: 12/06/2021 17:04:31
LastEditTime: 12/06/2021 17:04:31
Description: BST, delete node
"""

from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        if root is None:
            return None
        if root.val == key:
            # need to consider how to return the value
            if root.right:
                # find the smallest node in root.right
                curr = root.right
                while curr.left:
                    curr = curr.left
                curr.left = root.left
                return root.right
            else:
                return root.left

        if root.val < key:
            root.right = self.deleteNode(root.right, key)
        else:
            root.left = self.deleteNode(root.left, key)

        return root
