from typing import Optional
from leetcode.common.py_utils import ListNode


class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:

        def find_middle(head):
            slow = head
            fast = head
            while fast.next and fast.next.next:
                slow = slow.next
                fast = fast.next.next
            return slow

        def reverse(head):
            dummy = ListNode(next=head)
            prev = dummy
            curr = head
            while curr and curr.next:
                nxt = curr.next
                curr.next = nxt.next
                nxt.next = prev.next
                prev.next = nxt
            return dummy.next

        mid = find_middle(head)
        reverse_head = reverse(mid.next)
        mid.next = None
        p = head
        q = reverse_head
        while p and q:
            if p.val != q.val:
                return False
            p = p.next
            q = q.next
        return True
