from random import randrange


class RandomizedSet:

    # solution1: maintain the size of arr
    def __init__(self):
        self.arr = []
        self.size = 0
        self.m = {}

    def insert(self, val: int) -> bool:
        if val in self.m:
            return False

        if not self.arr or len(self.arr) <= self.size:
            self.arr.append(val)
        else:
            # we have larger arr and didn't used up yet (probably due to remove)
            self.arr[self.size] = val

        self.m[val] = self.size
        self.size += 1
        return True

    def remove(self, val: int) -> bool:
        if val not in self.m:
            return False
        idx = self.m[val]

        def exch(arr, i, j):
            arr[i], arr[j] = arr[j], arr[i]

        tail_idx = self.size - 1
        tail_value = self.arr[tail_idx]

        exch(self.arr, idx, tail_idx)

        self.m[tail_value] = idx
        self.size -= 1
        del self.m[val]
        return True

    def getRandom(self) -> int:
        random_idx = randrange(0, self.size)
        return self.arr[random_idx]


class RandomizedSet2:

    # solution2: always pop out the last element and check if its same as remove value.
    def __init__(self):
        self.map = {}
        self.arr = []

    def insert(self, val: int) -> bool:
        if val in self.map:
            return False

        self.map[val] = len(self.arr)
        self.arr.append(val)
        return True

    def remove(self, val: int) -> bool:
        if val not in self.map:
            return False

        # pop out the last element
        idx = self.map[val]
        del self.map[val]

        tail_idx = len(self.arr) - 1
        tail_value = self.arr.pop()

        # if the removed val is not the end of element.
        if idx != tail_idx:
            self.arr[idx] = tail_value
            self.map[tail_value] = idx

        return True

    def getRandom(self) -> int:
        return self.arr[randrange(0, len(self.arr))]
