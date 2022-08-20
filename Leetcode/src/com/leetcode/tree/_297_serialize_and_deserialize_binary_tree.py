'''
Date: 08/14/2022 14:29:52
LastEditTime: 08/14/2022 14:31:19
Description: BFS
'''
from collections import deque


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:

    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """
        if not root:
            return ""
        queue = deque()
        queue.append(root)
        res = []
        while len(queue):
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                if not curr:
                    res.append('*')
                    continue
                else:
                    res.append(str(curr.val))
                queue.append(curr.left)
                queue.append(curr.right)

        return ','.join(res)

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        if data == "":
            return None
        splits = data.split(',')
        root = TreeNode(int(splits[0]))
        idx = 1
        queue = deque()
        queue.append(root)
        while len(queue) and idx < len(splits):
            curr = queue.popleft()
            if not curr:
                continue
            left = splits[idx]
            if left != '*':
                curr.left = TreeNode(int(left))
            else:
                curr.left = None
            queue.append(curr.left)
            idx += 1
            right = splits[idx]
            if right != '*':
                curr.right = TreeNode(int(right))
            else:
                curr.right = None
            queue.append(curr.right)
            idx += 1
        return root
