from typing import Optional
from leetcode.common.py_utils import TreeNode


class Solution:
    def upsideDownBinaryTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root or not root.left:
            return root

        left = root.left
        right = root.right
        new_root = self.upsideDownBinaryTree(left)

        # 用left作为root，1）左子树是以前的右子树 2）右子树是以前的根
        left.left = right
        left.right = root

        root.left = None
        root.right = None
        # 这里的new_root 就是最后的root，一直传上来
        return new_root
