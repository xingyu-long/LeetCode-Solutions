from typing import Optional, Tuple

from common.py_utils import TreeNode


class Solution:
    # time:O(N)
    # space: O(N)
    def rob(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode]) -> Tuple[int, int]:
            if root is None:
                return 0, 0

            left_rob, left_not = dfs(root.left)
            right_rob, right_not = dfs(root.right)

            rob = left_not + right_not + root.val
            not_rob = max(left_rob, left_not) + max(right_rob, right_not)
            return rob, not_rob

        # rob or not
        return max(dfs(root))
