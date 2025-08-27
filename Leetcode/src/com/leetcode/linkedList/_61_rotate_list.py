from typing import Optional

from common.py_utils import ListNode


class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        if head is None:
            return None

        last_node = head
        length = 1
        while last_node.next:
            last_node = last_node.next
            length += 1

        k %= length

        # make it as circle
        last_node.next = head

        # find the place to cut the circle
        curr = head
        for _ in range(length - k - 1):
            curr = curr.next

        res = curr.next
        curr.next = None

        return res
