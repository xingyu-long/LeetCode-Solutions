from typing import Optional
from leetcode.common.py_utils import TreeNode


class Solution:
    def insertIntoBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
        if not root:
            return TreeNode(val)

        if root.val < val:
            root.right = self.insertIntoBST(root.right, val)
        else:
            root.left = self.insertIntoBST(root.left, val)

        return root


class Solution2:
    # iterative
    def insertIntoBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
        if not root:
            return TreeNode(val)

        curr = root
        prev = curr
        while curr:
            prev = curr
            if curr.val < val:
                curr = curr.right
                if curr == None:
                    prev.right = TreeNode(val)
            else:
                curr = curr.left
                if curr == None:
                    prev.left = TreeNode(val)

        return root
