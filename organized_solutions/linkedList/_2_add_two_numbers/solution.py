from typing import Optional

from leetcode.common.py_utils import ListNode


class Solution:
    def addTwoNumbers(
        self, l1: Optional[ListNode], l2: Optional[ListNode]
    ) -> Optional[ListNode]:
        dummy = ListNode()
        curr = dummy
        total = 0
        while l1 or l2 or total:
            if l1:
                total += l1.val
                l1 = l1.next
            if l2:
                total += l2.val
                l2 = l2.next

            curr.next = ListNode(total % 10)
            total //= 10
            curr = curr.next

        return dummy.next
