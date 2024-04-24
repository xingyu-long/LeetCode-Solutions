class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Node:
    def __init__(self, x: int, next: "Node" = None, random: "Node" = None):
        self.val = int(x)
        self.next = next
        self.random = random

class TrieNode:
    def __init__(self):
        self.children = dict()
        self.is_word = False
        self.word = None

    def __repr__(self):
        return f"children={self.children}, is_word={self.is_word}, word={self.word}"