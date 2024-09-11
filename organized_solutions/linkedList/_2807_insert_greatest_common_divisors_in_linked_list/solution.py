from typing import Optional
from leetcode.common.py_utils import ListNode


class Solution:
    def insertGreatestCommonDivisors(
        self, head: Optional[ListNode]
    ) -> Optional[ListNode]:

        def gcd(a, b):
            if a == 0:
                return b
            return gcd(b % a, a)

        dummy = ListNode(next=head)
        curr = head
        while curr and curr.next:
            nxt = curr.next
            common = ListNode(gcd(curr.val, nxt.val))
            curr.next = common
            common.next = nxt
            curr = nxt

        return dummy.next
