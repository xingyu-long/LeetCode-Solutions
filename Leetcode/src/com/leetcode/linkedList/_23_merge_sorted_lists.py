from heapq import heappop, heappush
from typing import List, Optional

from leetcode.common.py_utils import ListNode


class Solution:
    # Time: O(M * K * logK), where K <= 10^4 is length of lists, M <= 500 is number of elements each list.
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        # use heap
        ListNode.__lt__ = lambda self, other: self.val < other.val

        heap = []
        dummy = ListNode()
        curr = dummy
        for l in lists:
            if l:
                heappush(heap, l)
        while heap:
            head = heappop(heap)
            curr.next = head
            curr = curr.next
            if head.next:
                heappush(heap, head.next)

        return dummy.next


class Solution2:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:

        def merge(
            list1: Optional[ListNode], list2: Optional[ListNode]
        ) -> Optional[ListNode]:
            if not list1:
                return list2
            if not list2:
                return list1
            if list1.val < list2.val:
                list1.next = merge(list1.next, list2)
                return list1
            else:
                list2.next = merge(list1, list2.next)
                return list2

        def sort(lists: Optional[ListNode], start: int, end: int) -> Optional[ListNode]:
            if start > end:
                return None
            if start == end:
                return lists[start]

            mid = start + (end - start) // 2
            l1 = sort(lists, start, mid)
            l2 = sort(lists, mid + 1, end)
            return merge(l1, l2)

        return sort(lists, 0, len(lists) - 1)
