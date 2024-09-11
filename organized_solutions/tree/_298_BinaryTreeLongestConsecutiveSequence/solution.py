from typing import Optional
from leetcode.common.py_utils import TreeNode


class Solution:
    def longestConsecutive(self, root: Optional[TreeNode]) -> int:
        res = 0

        def dfs(root):
            nonlocal res

            if not root:
                return 0

            left = dfs(root.left)
            right = dfs(root.right)
            # 默认没有连续的情况
            l, r = 1, 1
            if root.right and root.val + 1 == root.right.val:
                r = right + 1
            if root.left and root.val + 1 == root.left.val:
                l = left + 1

            # 一旦中断，我们会reset为1，所以需要在这比较最大值
            res = max(res, l, r)
            return max(l, r)

        dfs(root)
        return res
