# Definition for a Node.
class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None


class Solution:
    # time: (len(p) + len(q))
    def lowestCommonAncestor(self, p: "Node", q: "Node") -> "Node":
        i, j = p, q

        while i != j:
            i = i.parent if i.parent else q
            j = j.parent if j.parent else p

        return i
