from math import inf
from typing import Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:

        def dfs(root, curr_min: int, curr_max: int) -> bool:
            if not root:
                return True
            if root.val <= curr_min or root.val >= curr_max:
                return False

            left = dfs(root.left, curr_min, min(curr_max, root.val))
            right = dfs(root.right, max(curr_min, root.val), curr_max)
            return left and right

        return dfs(root, -inf, inf)
