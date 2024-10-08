from leetcode.common.py_utils import TreeNode


class Solution:
    def averageOfSubtree(self, root: TreeNode) -> int:
        res = 0

        def dfs(root):
            if not root:
                return 0, 0
            nonlocal res

            left_total, left_count = dfs(root.left)
            right_total, right_count = dfs(root.right)

            count = left_count + right_count + 1
            total = left_total + right_total + root.val
            if total // count == root.val:
                res += 1

            return total, count

        dfs(root)
        return res
