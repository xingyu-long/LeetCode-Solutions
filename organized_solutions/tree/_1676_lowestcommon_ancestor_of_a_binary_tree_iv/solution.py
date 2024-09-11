from typing import List
from leetcode.common.py_utils import TreeNode


class Solution:
    def lowestCommonAncestor(
        self, root: "TreeNode", nodes: "List[TreeNode]"
    ) -> "TreeNode":
        s = set(nodes)

        def dfs(root):
            if not root:
                return None

            # 因为题目提到了一定存在，所以并不需要去数个数核对nodes的个数
            # 而是简单的用一个set来表示是否属于这个set
            if root in s:
                return root

            left = dfs(root.left)
            right = dfs(root.right)

            if left and right:
                return root
            if left:
                return left
            return right

        return dfs(root)


class Solution:
    # 利用数个数来确定res以及子树确实有所有的nodes
    def lowestCommonAncestor(
        self, root: "TreeNode", nodes: "List[TreeNode]"
    ) -> "TreeNode":
        s = set(nodes)
        res = None

        def dfs(root):
            if not root:
                return 0
            nonlocal res
            left = dfs(root.left)
            right = dfs(root.right)
            total = left + right
            if root in s:
                total += 1
            if total == len(s) and res is None:
                res = root
            return total

        dfs(root)
        return res
