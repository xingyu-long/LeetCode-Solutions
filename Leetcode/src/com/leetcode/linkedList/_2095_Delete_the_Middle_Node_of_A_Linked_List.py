'''
Date: 12/27/2021 21:34:53
LastEditTime: 12/27/2021 21:34:55
Description: Slow, Fast
'''
# Definition for singly-linked list.


from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def deleteMiddle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head.next:
            return None
        slow, fast = head, head.next.next

        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next

        slow.next = slow.next.next
        return head
