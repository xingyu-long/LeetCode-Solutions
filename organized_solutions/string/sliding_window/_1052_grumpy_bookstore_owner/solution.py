from collections import deque
from typing import List


class Solution:
    # time: O(n)
    def maxSatisfied(
        self, customers: List[int], grumpy: List[int], minutes: int
    ) -> int:
        n = len(customers)
        s = set()
        res = 0
        for i in range(n):
            if grumpy[i] == 0:
                s.add(i)
                res += customers[i]
        # get original result
        queue = deque()
        # try with window
        additional = 0
        max_additional = 0
        for i in range(n):
            queue.append(i)
            if grumpy[i] == 1:
                additional += customers[i]
            if len(queue) > minutes:
                left = queue.popleft()
                if grumpy[left] == 1:
                    additional -= customers[left]
            max_additional = max(additional, max_additional)
        return res + max_additional
