from typing import Optional
from leetcode.common.py_utils import TreeNode, ListNode


class Solution:
    def isSubPath(self, head: Optional[ListNode], root: Optional[TreeNode]) -> bool:
        if not head:
            return True

        if not root:
            return False

        def search(head, root):
            if not head:
                return True

            if not root:
                return False

            if head.val != root.val:
                return False

            return search(head.next, root.left) or search(head.next, root.right)

        # start from anywhere of the tree
        return (
            search(head, root)
            or self.isSubPath(head, root.left)
            or self.isSubPath(head, root.right)
        )
