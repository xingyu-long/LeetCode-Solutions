from common.py_utils import TreeNode


class Solution:
    def replaceValueInTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        m = defaultdict(int)

        def traversal(root, depth):
            if not root:
                return

            m[depth] += root.val
            depth += 1
            traversal(root.left, depth)
            traversal(root.right, depth)

        def replace(root, sibiling_sum, depth):
            if not root:
                return

            root.val = m[depth] - sibiling_sum
            sibiling_sum = (root.left.val if root.left else 0) + (
                root.right.val if root.right else 0
            )
            depth += 1
            replace(root.left, sibiling_sum, depth)
            replace(root.right, sibiling_sum, depth)

        traversal(root, 0)
        replace(root, root.val, 0)
        return root
