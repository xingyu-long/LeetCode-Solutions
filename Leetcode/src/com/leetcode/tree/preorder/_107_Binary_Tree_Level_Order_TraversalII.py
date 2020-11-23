'''
Date: 11/17/2020 21:04:26
LastEditTime: 11/17/2020 21:05:09
Description: DFS
'''


def levelOrderBottom(self, root: TreeNode) -> List[List[int]]:
    if not root:
        return []
    res = []
    self.dfs(root, res, 0)
    return res


def dfs(self, root, res, depth):
    if not root:
        return
    if len(res) == depth:
        res.insert(0, [])

    res[len(res) - 1 - depth].append(root.val)

    self.dfs(root.left, res, depth + 1)
    self.dfs(root.right, res, depth + 1)
