from collections import defaultdict
from typing import Optional
from leetcode.common.py_utils import TreeNode


class Solution:
    # time: O(n^2)
    # time: O(n)
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:

        def find(root, total):
            res = 0

            if not root:
                return 0

            total -= root.val
            if total == 0:
                res += 1
            left = find(root.left, total)
            right = find(root.right, total)
            return left + right + res

        if not root:
            return 0

        ways = find(root, targetSum)
        left = self.pathSum(root.left, targetSum)
        right = self.pathSum(root.right, targetSum)
        return ways + left + right


class Solution:
    # prefix sum
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:
        # total -> frequency
        prefix_sum = defaultdict(int)
        # 对于刚好是targetSum的情况我们需要提前插入一个值在这
        prefix_sum[0] = 1
        res = 0

        def dfs(root, total):
            nonlocal res

            if not root:
                return

            total += root.val
            if total - targetSum in prefix_sum:
                # it means we have total - {x} == targetSum
                res += prefix_sum[total - targetSum]

            prefix_sum[total] += 1
            dfs(root.left, total)
            dfs(root.right, total)
            prefix_sum[total] -= 1

        dfs(root, 0)
        return res
