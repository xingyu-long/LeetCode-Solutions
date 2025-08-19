from typing import List, Optional

from common.py_utils import TreeNode


class Solution:
    def findLeaves(self, root: Optional[TreeNode]) -> List[List[int]]:
        # depth in reverse order
        res = []

        def dfs(curr):
            if curr is None:
                return -1

            left = dfs(curr.left)
            right = dfs(curr.right)
            depth = max(left, right) + 1
            if len(res) == depth:
                res.append([])
            res[depth].append(curr.val)

            return depth

        dfs(root)
        return res
