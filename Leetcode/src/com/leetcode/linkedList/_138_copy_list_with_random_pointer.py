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


class Solution2:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        curr = head
        dummy = Node(0)

        # 1. add new node and concat with old list
        while curr:
            nxt = curr.next
            new_node = Node(curr.val)
            curr.next = new_node
            new_node.next = nxt
            curr = nxt

        curr = head
        # 2. set up random for new nodes
        while curr:
            if curr.random:
                curr.next.random = curr.random.next
            curr = curr.next.next

        # 3. set up next for new nodes and restore the changes on old list
        tail = dummy
        curr = head
        while curr:
            nxt = curr.next.next
            copy = curr.next

            tail.next = copy
            tail = tail.next

            # restore it
            curr.next = nxt
            curr = nxt

        return dummy.next
