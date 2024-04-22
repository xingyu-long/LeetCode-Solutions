from typing import Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    # time:O(n)
    def isBalanced(self, root: Optional[TreeNode]) -> bool:

        def dfs(root: Optional[TreeNode]) -> tuple[bool, int]:
            if not root:
                return True, 0

            left_balance, left_len = dfs(root.left)
            right_balance, right_len = dfs(root.right)

            balance = left_balance and right_balance and abs(left_len - right_len) <= 1
            return balance, max(left_len, right_len) + 1

        res, _ = dfs(root)
        return res
