from typing import List, Optional

from leetcode.common.py_utils import TreeNode


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:

        def dfs(preorder, p_start, p_end, inorder, i_start, i_end):
            if p_start > p_end or i_start > i_end:
                return None
            curr = preorder[p_start]
            # find the split point
            split = 0
            for i in range(i_start, i_end + 1):
                if inorder[i] == curr:
                    split = i
                    break

            count = split - i_start
            root = TreeNode(curr)
            root.left = dfs(
                preorder, p_start + 1, p_start + count, inorder, i_start, split - 1
            )
            root.right = dfs(
                preorder, p_start + 1 + count, p_end, inorder, split + 1, i_end
            )

            return root

        return dfs(preorder, 0, len(preorder) - 1, inorder, 0, len(inorder) - 1)


class Solution:
    # easy-to-understand
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if not preorder or not inorder:
            return None

        root = TreeNode(preorder[0])
        mid = inorder.index(preorder[0])
        root.left = self.buildTree(preorder[1 : mid + 1], inorder[:mid])
        root.right = self.buildTree(preorder[mid + 1 :], inorder[mid + 1 :])

        return root
