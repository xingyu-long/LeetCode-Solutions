from collections import deque
from typing import List, Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        def dfs(root, depth: int, res: List[List[int]]) -> None:
            if not root:
                return
            if len(res) == depth:
                res.append([])

            res[depth].append(root.val)
            dfs(root.left, depth + 1, res)
            dfs(root.right, depth + 1, res)

        res = []
        dfs(root, 0, res)
        return res


class Solution2:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []

        queue = deque()
        res = []
        queue.append(root)
        while queue:
            path = []
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                path.append(curr.val)
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
            res.append(path)

        return res
