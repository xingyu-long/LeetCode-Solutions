from math import inf
from typing import Optional
from leetcode.common.py_utils import TreeNode


class Solution:
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        diff = inf
        res = -1
        curr = root
        while curr:
            if abs(target - curr.val) < diff:
                res = curr.val
                diff = abs(target - curr.val)
            elif abs(target - curr.val) == diff:
                # If there are multiple answers, print the smallest
                res = min(res, curr.val)

            curr = curr.left if target < curr.val else curr.right
        return res
