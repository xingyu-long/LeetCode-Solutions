from typing import Optional

from leetcode.common.py_utils import ListNode


class Solution:
    def reorderList(self, head: Optional[ListNode]) -> None:
        """
        Do not return anything, modify head in-place instead.
        """

        def find_mid(head: ListNode) -> Optional[ListNode]:
            slow = fast = head
            while fast.next and fast.next.next:
                slow = slow.next
                fast = fast.next.next
            return slow

        def reverse(head: ListNode) -> Optional[ListNode]:
            dummy = ListNode(next=head)
            curr = dummy.next
            while curr and curr.next:
                temp = curr.next
                curr.next = temp.next
                temp.next = dummy.next
                dummy.next = temp
            return dummy.next

        l1 = head
        mid = find_mid(head)
        l2 = reverse(mid.next)
        mid.next = None
        while l1 and l2:
            next_l1 = l1.next
            next_l2 = l2.next

            l1.next = l2
            l2.next = next_l1

            l1 = next_l1
            l2 = next_l2
