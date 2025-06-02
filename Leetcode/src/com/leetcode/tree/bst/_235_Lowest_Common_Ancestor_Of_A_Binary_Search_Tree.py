"""
Date: 11/20/2020 19:49:08
LastEditTime: 11/20/2020 19:49:44
Description: LCA
"""

from leetcode.common.py_utils import TreeNode


class Solution:
    def lowestCommonAncestor(
        self, root: "TreeNode", p: "TreeNode", q: "TreeNode"
    ) -> "TreeNode":
        curr = root
        while curr:
            if curr.val > p.val and curr.val > q.val:
                curr = curr.left
            elif curr.val < p.val and curr.val < q.val:
                curr = curr.right
            else:
                break
        return curr
