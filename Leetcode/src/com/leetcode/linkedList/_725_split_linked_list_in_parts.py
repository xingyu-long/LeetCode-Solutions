from typing import List, Optional
from leetcode.common.py_utils import ListNode


# similar to 2028. Find Missing Observations
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def splitListToParts(
        self, head: Optional[ListNode], k: int
    ) -> List[Optional[ListNode]]:
        if not head:
            return [None] * k

        num = 0
        curr = head
        while curr:
            num += 1
            curr = curr.next

        # calculate the length of each part
        avg = num // k
        remain = num % k

        res = []
        prev, curr = None, head
        for _ in range(k):
            res.append(curr)
            for _ in range(avg + (1 if remain > 0 else 0)):
                prev, curr = curr, curr.next
            prev.next = None
            remain -= 1

        return res
