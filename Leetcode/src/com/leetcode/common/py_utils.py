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
        self.children: dict[str, "TrieNode"] = {}
        self.is_word = False
        self.word: str | None = None

    def __repr__(self):
        return f"children={self.children}, is_word={self.is_word}, word={self.word}"


class NestedInteger:
    def __init__(self, value=None):
        """
        If value is not specified, initializes an empty list.
        Otherwise initializes a single integer equal to value.
        """

    def isInteger(self):
        """
        @return True if this NestedInteger holds a single integer, rather than a nested list.
        :rtype bool
        """

    def add(self, elem):
        """
        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
        :rtype void
        """

    def setInteger(self, value):
        """
        Set this NestedInteger to hold a single integer equal to value.
        :rtype void
        """

    def getInteger(self):
        """
        @return the single integer that this NestedInteger holds, if it holds a single integer
        Return None if this NestedInteger holds a nested list
        :rtype int
        """

    def getList(self):
        """
        @return the nested list that this NestedInteger holds, if it holds a nested list
        Return None if this NestedInteger holds a single integer
        :rtype List[NestedInteger]
        """


class FenwickTree:
    def __init__(self, n: int) -> None:
        self.tree = [0] * (n + 1)

    @staticmethod
    def _low_bit(x: int) -> int:
        return x & (-x)

    def update(self, i: int, delta: int) -> None:
        while i < len(self.tree):
            self.tree[i] += delta
            i += self._low_bit(i)

    def query(self, i: int) -> int:
        total = 0
        while i > 0:
            total += self.tree[i]
            i -= self._low_bit(i)
        return total


class UnionFind:
    def __init__(self, n: int) -> None:
        self.id = [i for i in range(n)]
        self.size = [1 for _ in range(n)]
        self.count = n

    def find(self, p: int) -> int:
        while p != self.id[p]:
            # path compression
            self.id[p] = self.id[self.id[p]]
            p = self.id[p]
        return p

    def union(self, p: int, q: int) -> bool:
        p_id, q_id = self.find(p), self.find(q)
        if p_id == q_id:
            # already connected
            return False

        if self.size[p] > self.size[q]:
            self.size[p] += self.size[q]
            self.id[q_id] = p_id
        else:
            self.size[q] += self.size[p]
            self.id[p_id] = q_id
        self.count -= 1
        return True
