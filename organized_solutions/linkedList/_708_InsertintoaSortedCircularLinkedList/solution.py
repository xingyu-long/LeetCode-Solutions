from typing import Optional
from leetcode.common.py_utils import ListNode as Node


class Solution:
    def insert(self, head: "Optional[Node]", insertVal: int) -> "Node":
        if not head:
            node = Node(insertVal)
            node.next = node
            return node

        curr = head
        nxt = curr.next
        while nxt != head:
            # [1] insert=0
            if curr.val <= insertVal <= nxt.val:
                break
            # [4,1] insert value could be [5] or [0]
            if curr.val > nxt.val and (insertVal >= curr.val or insertVal <= nxt.val):
                break
            curr = nxt
            nxt = nxt.next

        curr.next = Node(insertVal, nxt)
        return head
