from collections import deque
from typing import Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    # BFS
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        queue = deque()
        queue.append(p)
        queue.append(q)
        while queue:
            first = queue.popleft()
            second = queue.popleft()
            if first and second:
                if first.val != second.val:
                    return False
            elif first or second:
                return False
            else:
                # first and second both are None
                continue
            queue.append(first.left)
            queue.append(second.left)
            queue.append(first.right)
            queue.append(second.right)
        return True


class Solution2:
    # DFS
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if not p and not q:
            return True
        if not p or not q:
            return False

        # p and q is not None
        if p.val != q.val:
            return False

        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
