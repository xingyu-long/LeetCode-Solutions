from collections import deque

from leetcode.common.py_utils import TreeNode


class Codec:

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
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                if not curr:
                    res.append("#")
                else:
                    res.append(str(curr.val))
                    queue.append(curr.left)
                    queue.append(curr.right)
        return ",".join(res)

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        if len(data) == 0:
            return None
        arr = data.split(",")
        i = 0
        root = TreeNode(int(arr[i]))
        i += 1
        queue = deque()
        queue.append(root)
        while queue and i < len(arr):
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                left = arr[i]
                i += 1
                right = arr[i]
                i += 1
                if left != "#":
                    curr.left = TreeNode(int(left))
                    queue.append(curr.left)
                if right != "#":
                    curr.right = TreeNode(int(right))
                    queue.append(curr.right)
        return root


class Codec2:

    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """
        if not root:
            return "#"
        left = self.serialize(root.left)
        right = self.serialize(root.right)
        return f"{root.val},{left},{right}"

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """

        def dfs(queue: deque):
            curr = queue.popleft()
            if curr == "#":
                return None
            root = TreeNode(int(curr))
            root.left = dfs(queue)
            root.right = dfs(queue)
            return root

        if not data:
            return None
        queue = deque(data.split(","))
        return dfs(queue)
