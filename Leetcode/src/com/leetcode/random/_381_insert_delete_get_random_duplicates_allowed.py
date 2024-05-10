from collections import defaultdict
from random import randrange


class RandomizedCollection:

    def __init__(self):
        self.arr = []
        # val -> {idx0, idx1... idxN}
        self.map = defaultdict(set)

    def insert(self, val: int) -> bool:
        self.map[val].add(len(self.arr))
        self.arr.append(val)
        return len(self.map[val]) == 1

    def remove(self, val: int) -> bool:
        if val not in self.map:
            return False

        idx = self.map[val].pop()
        tail_idx = len(self.arr) - 1
        tail_val = self.arr.pop()

        if len(self.map[val]) == 0:
            del self.map[val]

        if idx != tail_idx:
            self.map[tail_val].remove(tail_idx)
            self.map[tail_val].add(idx)
            self.arr[idx] = tail_val

        return True

    def getRandom(self) -> int:
        return self.arr[randrange(0, len(self.arr))]
