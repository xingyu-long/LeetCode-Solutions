from sortedcontainers import SortedList


class MyCalendar:

    def __init__(self):
        self.arr = []

    # unsorted
    # time: O(n), space: O(n)
    def book(self, start: int, end: int) -> bool:
        for duration in self.arr:
            curr_start, curr_end = duration
            if start < curr_end and start >= curr_start:
                return False
            if end > curr_start and start <= curr_start:
                return False
        self.arr.append((start, end))
        return True


class MyCalendar2:

    def __init__(self):
        self.data = SortedList(
            [(float("-inf"), float("-inf")), (float("inf"), float("inf"))]
        )

    # time: O(logN)
    def book(self, start: int, end: int) -> bool:
        idx = self.data.bisect_right((start, end))
        if start < self.data[idx - 1][1] or end > self.data[idx][0]:
            return False
        self.data.add((start, end))
        return True


class Node:
    def __init__(self, start, end):
        self.start = start
        self.end = end
        self.left = None
        self.right = None


class MyCalendar3:

    def __init__(self):
        self.root = None

    def helper(self, start: int, end: int, node: Node) -> bool:
        if start >= node.end:
            # go to the right tree
            if node.right:
                return self.helper(start, end, node.right)
            else:
                node.right = Node(start, end)
                return True
        elif end <= node.start:
            # go to the left tree
            if node.left:
                return self.helper(start, end, node.left)
            else:
                node.left = Node(start, end)
                return True
        else:
            return False

    # time: O(logN)
    def book(self, start: int, end: int) -> bool:
        # use binary search tree to keep it sorted
        if not self.root:
            self.root = Node(start, end)
            return True

        return self.helper(start, end, self.root)
