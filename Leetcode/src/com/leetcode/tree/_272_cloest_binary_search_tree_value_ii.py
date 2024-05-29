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
    # O(logN + K)
    def closestKValues(
        self, root: Optional[TreeNode], target: float, k: int
    ) -> List[int]:
        pre, succ = [], []
        curr = root
        while curr:
            if curr.val <= target:
                pre.append(curr)
                curr = curr.right
            else:
                succ.append(curr)
                curr = curr.left

        def get_predecessor(pre):
            curr = pre.pop()
            if curr.left:
                pre.append(curr.left)
                curr = curr.left
                while curr and curr.right:
                    pre.append(curr.right)
                    curr = curr.right

        def get_successor(succ):
            curr = succ.pop()
            if curr.right:
                succ.append(curr.right)
                curr = curr.right
                while curr and curr.left:
                    succ.append(curr.left)
                    curr = curr.left

        total = 0
        res = []
        while total < k:
            if not succ or (pre and target - pre[-1].val < succ[-1].val - target):
                res.append(pre[-1].val)
                get_predecessor(pre)
            else:
                res.append(succ[-1].val)
                get_successor(succ)

            total += 1

        return res
