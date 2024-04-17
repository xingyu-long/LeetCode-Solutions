from typing import Optional
from leetcode.common.py_utils import Node


class Solution:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        if not head:
            return None
        # hash table
        d = {None: None}
        curr = head
        # copy all nodes
        while curr:
            d[curr] = Node(curr.val)
            curr = curr.next
        # build next and random pointer?
        curr = head
        while curr:
            temp = curr.next
            random = curr.random
            d[curr].next = d[temp]
            d[curr].random = d[random]
            curr = temp
        return d[head]
