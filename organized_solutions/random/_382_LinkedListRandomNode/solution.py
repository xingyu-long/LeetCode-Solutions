from random import randrange
from typing import Optional

from leetcode.common.py_utils import ListNode


class Solution:
    """
    [1, 2, 3]

    for 1, 1 * (1/2) * (2/3) -> 1/3
    for 2, (1/2) * (2/3) -> 1/3
    for 3, 1/3
    """

    def __init__(self, head: Optional[ListNode]):
        self.head = head

    def getRandom(self) -> int:
        curr = self.head
        count = 0
        res = -1
        while curr:
            count += 1
            random = randrange(0, count)
            if random == 0:
                res = curr.val
            curr = curr.next
        return res
