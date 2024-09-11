from typing import Optional
from math import inf

from leetcode.common.py_utils import TreeNode


class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        res = -inf

        def dfs(root):
            nonlocal res

            if not root:
                return 0

            # 忽略那些为负的子树，还不如不包括进来
            left = max(dfs(root.left), 0)
            right = max(dfs(root.right), 0)
            res = max(res, left + right + root.val)
            return max(left, right) + root.val

        dfs(root)
        return res
