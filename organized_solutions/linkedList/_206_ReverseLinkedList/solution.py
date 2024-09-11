from typing import Optional
from leetcode.common.py_utils import ListNode


class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode(next=head)
        curr = head
        while curr and curr.next:
            temp = curr.next
            curr.next = temp.next
            temp.next = dummy.next
            dummy.next = temp

        return dummy.next
