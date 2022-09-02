# Definition for a binary tree node.
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def getDirections(self, root: Optional[TreeNode], startValue: int, destValue: int) -> str:

        def find_ancestor(root, start_value, dest_value):
            if not root:
                return None
            if root.val == start_value or root.val == dest_value:
                return root

            left = find_ancestor(root.left, start_value, dest_value)
            right = find_ancestor(root.right, start_value, dest_value)
            if left and right:
                return root
            if not left:
                return right
            return left

        def find_target(root, target, path):
            if not root:
                return False
            if root.val == target:
                return True

            path.append('L')
            if find_target(root.left, target, path):
                return True
            path.pop()

            path.append('R')
            if find_target(root.right, target, path):
                return True
            path.pop()

            return False

        def reverse_path(path):
            return [('U' if d in ['L', 'R'] else d) for d in reversed(path)]

        ancestor = find_ancestor(root, startValue, destValue)
        left_path, right_path = [], []
        find_target(ancestor, startValue, left_path)
        find_target(ancestor, destValue, right_path)
        return ''.join(reverse_path(left_path) + right_path)
