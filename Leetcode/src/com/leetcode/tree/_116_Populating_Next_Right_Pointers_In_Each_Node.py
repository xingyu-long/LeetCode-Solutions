# Definition for a Node.
from typing import Optional


class Node:
    def __init__(
        self,
        val: int = 0,
        left: "Node" = None,
        right: "Node" = None,
        next: "Node" = None,
    ):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


class Solution:
    def connect(self, root: "Optional[Node]") -> "Optional[Node]":
        if not root:
            return None

        if root.left and root.right:
            root.left.next = root.right

        # 没有考虑到中间链接的情况
        if root.right and root.next:
            root.right.next = root.next.left

        self.connect(root.left)
        self.connect(root.right)

        return root

    def connect2(self, root: "Optional[Node]") -> "Optional[Node]":
        curr = root
        while curr:
            next_level = curr.left
            while curr:
                if curr.left and curr.right:
                    curr.left.next = curr.right
                if curr.right and curr.next:
                    curr.right.next = curr.next.left
                curr = curr.next
            curr = next_level
        return root
