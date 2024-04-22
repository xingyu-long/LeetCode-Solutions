from typing import Optional
from leetcode.common.py_utils import TreeNode


class Solution:
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        def is_same_tree(p, q) -> bool:
            if not p and not q:
                return True
            if not p or not q:
                return False
            if p.val != q.val:
                return False
            return is_same_tree(p.left, q.left) and is_same_tree(p.right, q.right)

        if not root or not subRoot:
            return False

        if is_same_tree(root, subRoot):
            return True

        return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)
