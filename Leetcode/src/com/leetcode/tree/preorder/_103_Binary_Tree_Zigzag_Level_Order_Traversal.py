from typing import List
from leetcode.common.py_utils import TreeNode


def zigzagLevelOrder(self, root: TreeNode) -> List[List[int]]:
    if not root:
        return []
    res = []
    self.dfs(root, res, 0)
    return res


def dfs(self, root, res, depth):
    if not root:
        return

    if len(res) == depth:
        res.append([])

    if depth % 2 == 0:
        res[depth].append(root.val)
    else:
        res[depth].insert(0, root.val)

    self.dfs(root.left, res, depth + 1)
    self.dfs(root.right, res, depth + 1)
