'''
Date: 08/19/2021 19:22:31
LastEditTime: 08/19/2021 19:23:00
Description: Tree, Sum
'''


class Solution:

    def maxProduct(self, root: Optional[TreeNode]) -> int:
        def dfs(root):
            if root == None:
                return 0

            left = dfs(root.left)
            right = dfs(root.right)
            sub = left + right + root.val
            self.res = max(self.res, (self.total - sub) * sub)
            return sub

        self.res, self.total = 0, 0
        self.total = dfs(root)
        dfs(root)
        return self.res % (10 ** 9 + 7)
