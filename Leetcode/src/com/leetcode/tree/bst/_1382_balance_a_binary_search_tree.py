from leetcode.common.py_utils import TreeNode


class Solution:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        arr = []

        def dfs(root):
            if not root:
                return None

            dfs(root.left)
            arr.append(root)
            dfs(root.right)

        def build(left, right):
            if left > right:
                return None

            mid = left + (right - left) // 2
            root = TreeNode(arr[mid].val)
            root.left = build(left, mid - 1)
            root.right = build(mid + 1, right)
            return root

        dfs(root)
        return build(0, len(arr) - 1)
