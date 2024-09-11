'''
Date: 08/10/2022 13:57:04
LastEditTime: 08/10/2022 14:00:19
Description: Tree, preorder, postorder
'''


# Definition for a binary tree node.
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    '''
    解题思路：
    这个题一开始被这个结果所迷惑，会觉得特别难，但是只要是树的问题，始终
    就只有三种遍历顺序解决问题。这个问题我们分成三个部分，一个是专门收集左子树的
    一个是收集所有的叶子节点，最后一个则是利用后序遍历去收集右子树的情况
    '''
    # time: O(n) space: O(1) except result list
    def boundaryOfBinaryTree(self, root: Optional[TreeNode]) -> List[int]:
        res = []

        def collect_left(root):
            if not root:
                return
            # all leaves will be handled by collect_leaves
            if (not root.left) and (not root.right):
                return
            res.append(root.val)
            if root.left:
                collect_left(root.left)
            else:
                collect_left(root.right)

        def collect_right(root):
            if not root:
                return
            # all leaves will be handled by collect_leaves
            if (not root.left) and (not root.right):
                return

            if root.right:
                collect_right(root.right)
            else:
                collect_right(root.left)
            # should be add anti-clockwise
            res.append(root.val)

        def collect_leaves(root):
            if not root:
                return
            if (not root.left) and (not root.right):
                res.append(root.val)
            collect_leaves(root.left)
            collect_leaves(root.right)

        if not root:
            return []
        res.append(root.val)
        collect_left(root.left)
        collect_leaves(root.left)
        collect_leaves(root.right)
        collect_right(root.right)
        return res
