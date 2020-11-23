'''
Date: 11/20/2020 19:21:15
LastEditTime: 11/20/2020 19:29:49
Description: BST, min, max
'''

from Leetcode.src.com.leetcode.common import TreeNode


class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        if not root:
            return True
        return self.dfs(root, None, None)


def dfs(self, root, curr_min, curr_max):
    if not root:
        return True

    if curr_min != None and root.val <= curr_min:
        return False
    if curr_max != None and root.val >= curr_max:
        return False

    return self.dfs(root.left, curr_min, root.val) and self.dfs(root.right, root.val, curr_max)
