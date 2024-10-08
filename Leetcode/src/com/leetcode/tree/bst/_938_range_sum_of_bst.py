from typing import Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    # time: O(n)
    # space: O(n)
    def rangeSumBST(self, root: Optional[TreeNode], low: int, high: int) -> int:
        if not root:
            return 0

        total = 0
        if low <= root.val <= high:
            total += root.val
        left = right = 0
        if root.val >= low:
            left = self.rangeSumBST(root.left, low, high)
        if root.val <= high:
            right = self.rangeSumBST(root.right, low, high)
        total += left + right
        return total
