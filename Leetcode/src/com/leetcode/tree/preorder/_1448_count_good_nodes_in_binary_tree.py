from leetcode.common.py_utils import TreeNode


class Solution:
    def goodNodes(self, root: TreeNode) -> int:

        res = 0

        def dfs(root, max_val: int):
            nonlocal res

            if not root:
                return

            if root.val >= max_val:
                res += 1
                max_val = root.val

            dfs(root.left, max_val)
            dfs(root.right, max_val)

        dfs(root, root.val)
        return res
