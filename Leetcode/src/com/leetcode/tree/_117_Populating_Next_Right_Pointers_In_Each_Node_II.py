'''
Date: 12/06/2020 09:41:37
LastEditTime: 12/06/2020 09:42:15
Description: Traversal by level.
'''


# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return None
        curr = root
        prev, next = None, None
        while curr:
            while curr:
                if curr.left:
                    if not next:
                        next = curr.left
                    if prev:
                        prev.next = curr.left
                    prev = curr.left

                if curr.right:
                    if not next:
                        next = curr.right
                    if prev:
                        prev.next = curr.right
                    prev = curr.right

                curr = curr.next

            curr = next
            prev, next = None, None
        return root
