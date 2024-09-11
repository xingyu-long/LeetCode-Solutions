from typing import Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        res = None

        def dfs(root):
            nonlocal k, res
            if not root:
                return

            dfs(root.left)
            k -= 1
            if k == 0:
                res = root.val
                return
            dfs(root.right)

        dfs(root)
        return res


class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        if not root:
            return -1

        stack = []
        curr = root
        while curr or stack:
            while curr:
                stack.append(curr)
                curr = curr.left
            curr = stack.pop()
            k -= 1
            if k == 0:
                return curr.val
            curr = curr.right
        return -1
