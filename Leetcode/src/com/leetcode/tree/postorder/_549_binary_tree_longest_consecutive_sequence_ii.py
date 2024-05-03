from typing import Optional
from leetcode.common.py_utils import TreeNode


class Result:
    def __init__(self, node, inc=1, dec=1):
        self.node = node
        self.inc = inc
        self.dec = dec


class Solution:
    def longestConsecutive(self, root: Optional[TreeNode]) -> int:
        res = 0

        def dfs(root) -> Optional[Result]:
            nonlocal res

            if not root:
                return None

            left = dfs(root.left)
            right = dfs(root.right)

            curr = Result(root)
            if left:
                if left.node.val - 1 == root.val:
                    curr.dec = max(curr.dec, left.dec + 1)
                elif left.node.val + 1 == root.val:
                    curr.inc = max(curr.inc, left.inc + 1)
            if right:
                if right.node.val - 1 == root.val:
                    curr.dec = max(curr.dec, right.dec + 1)
                elif right.node.val + 1 == root.val:
                    curr.inc = max(curr.inc, right.inc + 1)

            # print(f"root={root.val}, curr.inc={curr.inc}, curr.dec={curr.dec}")
            res = max(res, curr.inc + curr.dec - 1)
            return curr

        dfs(root)
        return res
