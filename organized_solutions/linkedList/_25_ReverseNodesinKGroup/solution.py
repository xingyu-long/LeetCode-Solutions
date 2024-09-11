from typing import Optional

from leetcode.common.py_utils import ListNode


class Solution:
    # time:O(n)
    # space: O(1)
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        def count(head):
            size = 0
            while head:
                size += 1
                head = head.next
            return size

        dummy = prev = ListNode(next=head)
        total = count(head)
        for _ in range(total // k):
            curr = prev.next
            for _ in range(k - 1):
                temp = curr.next
                curr.next = temp.next
                temp.next = prev.next
                prev.next = temp
            prev = curr
        return dummy.next
