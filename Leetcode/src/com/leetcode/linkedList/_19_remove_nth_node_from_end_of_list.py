from typing import Optional
from leetcode.common.py_utils import ListNode


class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        """
                       s         f
        D -> 1 -> 2 -> 3 -> 4 -> 5
        """
        dummy = ListNode(next=head)
        slow = fast = dummy
        for _ in range(n):
            fast = fast.next
        while fast and fast.next:
            slow = slow.next
            fast = fast.next

        slow.next = slow.next.next

        return dummy.next
