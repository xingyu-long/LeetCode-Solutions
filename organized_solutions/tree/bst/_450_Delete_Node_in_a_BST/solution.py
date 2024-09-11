'''
Date: 12/06/2021 17:04:31
LastEditTime: 12/06/2021 17:04:31
Description: BST, delete node
'''


from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        if not root:
            return root
        if key > root.val:
            root.right = self.deleteNode(root.right, key)
        elif key < root.val:
            root.left = self.deleteNode(root.left, key)
        else:
            if not root.left:
                return root.right
            elif not root.right:
                return root.left
            else:
                min_root = root.right
                # find the min
                while min_root.left:
                    min_root = min_root.left
                root.val = min_root.val
                root.right = self.deleteNode(root.right, root.val)
        return root
