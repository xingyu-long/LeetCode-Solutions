from typing import Optional
from leetcode.common.py_utils import ListNode, TreeNode


class Solution:
    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:

        def find_middle(start, end):
            slow = start
            fast = start
            while fast != end and fast.next != end:
                slow = slow.next
                fast = fast.next.next
            return slow

        def dfs(start, end):
            if start == end:
                return None

            mid = find_middle(start, end)
            left = dfs(start, mid)
            right = dfs(mid.next, end)
            root = TreeNode(mid.val)
            root.left = left
            root.right = right
            return root

        return dfs(head, None)
