# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children


class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        if not root:
            return []
        queue = [root]
        curr_size = 1
        next_size = 0
        res = []
        while queue:
            count = 0
            l = []
            while count < curr_size:
                curr = queue.pop(0)
                l.append(curr.val)
                for child in curr.children:
                    queue.append(child)
                    next_size += 1
                count += 1
            curr_size = next_size
            next_size = 0
            res.append(l)
        return res
