from typing import Optional

from leetcode.common.py_utils import ListNode


class Solution:
    def swapNodes(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        first, second = None, None
        curr = head
        while curr:
            k -= 1
            # use first as fast node and keep the relative distance between first and second
            second = None if second is None else second.next
            if k == 0:
                first = curr
                second = head

            curr = curr.next

        first.val, second.val = second.val, first.val
        return head
