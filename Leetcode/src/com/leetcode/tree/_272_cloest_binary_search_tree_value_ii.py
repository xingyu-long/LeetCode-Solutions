from collections import deque
from heapq import heappop, heappush
from typing import List, Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    def closestKValues(
        self, root: Optional[TreeNode], target: float, k: int
    ) -> List[int]:
        # klogk + N of tree
        # how to traversal it more efficiently?
        heap = []
        stack = []
        curr = root
        while stack or curr:
            while curr:
                stack.append(curr)
                curr = curr.left

            curr = stack.pop()
            heappush(heap, (-abs(curr.val - target), curr.val))
            if len(heap) > k:
                heappop(heap)
            curr = curr.right

        return [x[1] for x in heap]


class Solution:
    # time: O(N)
    def closestKValues(
        self, root: Optional[TreeNode], target: float, k: int
    ) -> List[int]:
        queue = deque()
        stack = []
        curr = root
        while stack or curr:
            while curr:
                stack.append(curr)
                curr = curr.left
            curr = stack.pop()

            if len(queue) == k:
                if abs(curr.val - target) < abs(queue[0] - target):
                    queue.popleft()
                else:
                    # earlier return
                    return queue
            queue.append(curr.val)
            curr = curr.right
        return queue
