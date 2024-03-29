'''
Date: 08/09/2022 21:42:37
LastEditTime: 08/09/2022 21:46:24
Description: LinkedList, slow & fast (find middle), reverse
'''
# Definition for singly-linked list.


from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    # time: O(n) space: O(1)
    def pairSum(self, head: Optional[ListNode]) -> int:
        # find the middle and reverse the second half
        slow, fast = head, head
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
        curr = slow.next
        slow.next = None
        new_head = None
        while curr:
            next_item = curr.next
            curr.next = new_head
            new_head = curr
            curr = next_item
        res = 0
        while head and new_head:
            res = max(res, head.val + new_head.val)
            head = head.next
            new_head = new_head.next
        return res
