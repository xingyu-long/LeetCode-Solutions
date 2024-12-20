from typing import Optional

from common.py_utils import TreeNode


class Solution:
    def reverseOddLevels(self, root: Optional[TreeNode]) -> Optional[TreeNode]:

        # similar to https://leetcode.com/problems/symmetric-tree/
        def dfs(node1, node2, flip):
            if node1 is None or node2 is None:
                return
            if flip:
                node1.val, node2.val = node2.val, node1.val

            flip = not flip
            dfs(node1.left, node2.right, flip)
            dfs(node1.right, node2.left, flip)

        dfs(root.left, root.right, True)
        return root
