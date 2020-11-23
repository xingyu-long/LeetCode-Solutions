'''
Date: 11/17/2020 21:26:21
LastEditTime: 11/17/2020 21:26:48
Description: Preorder, Right and left.
'''


def rightSideView(self, root: TreeNode) -> List[int]:
    if not root:
        return []
    res = []
    self.dfs(root, res, 0)
    return res


def dfs(self, root, res, depth):
    if not root:
        return

    if len(res) == depth:
        res.append(root.val)

    self.dfs(root.right, res, depth + 1)
    self.dfs(root.left, res, depth + 1)
