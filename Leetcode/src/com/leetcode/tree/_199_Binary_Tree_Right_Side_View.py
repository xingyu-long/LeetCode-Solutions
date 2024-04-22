from typing import List, Optional
from leetcode.common.py_utils import TreeNode


class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root, depth: int, path: List[int]) -> None:
            if not root:
                return
            if depth == len(path):
                path.append(root.val)
            dfs(root.right, depth + 1, path)
            dfs(root.left, depth + 1, path)

        res = []
        dfs(root, 0, res)
        return res
