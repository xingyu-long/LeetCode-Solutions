from typing import Optional

from common.py_utils import ListNode


class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(next=head)

        prev = dummy
        while prev.next and prev.next.next:
            curr = prev.next

            nxt = curr.next
            curr.next = nxt.next
            nxt.next = prev.next
            prev.next = nxt

            prev = curr

        return dummy.next

