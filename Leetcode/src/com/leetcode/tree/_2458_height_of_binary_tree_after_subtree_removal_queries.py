from functools import cache

from common.py_utils import TreeNode


class Solution:
    # time: O(N + # of queries)
    def treeQueries(self, root: Optional[TreeNode], queries: List[int]) -> List[int]:
        res = defaultdict(int)

        @cache
        def height(root):
            if not root:
                return 0

            left = height(root.left)
            right = height(root.right)
            return max(left, right) + 1

        def dfs(root, depth, longest):
            if not root:
                return
            res[root.val] = longest
            dfs(root.left, depth + 1, max(longest, depth + height(root.right)))
            dfs(root.right, depth + 1, max(longest, depth + height(root.left)))

        dfs(root, 0, 0)
        return [res[q] for q in queries]
