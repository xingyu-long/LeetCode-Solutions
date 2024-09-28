class MyCircularDeque:

    def __init__(self, k: int):
        self.arr = [-1] * k
        self.head = 0
        self.tail = k - 1
        self.size = 0
        self.total = k

    def insertFront(self, value: int) -> bool:
        if self.isFull():
            return False
        self.head -= 1
        self.head += self.total
        self.head %= self.total
        self.arr[self.head] = value
        self.size += 1
        return True

    def insertLast(self, value: int) -> bool:
        if self.isFull():
            return False
        self.tail += 1
        self.tail %= self.total
        self.arr[self.tail] = value
        self.size += 1
        return True

    def deleteFront(self) -> bool:
        if self.isEmpty():
            return False
        self.head += 1
        self.head %= self.total
        self.size -= 1
        return True

    def deleteLast(self) -> bool:
        if self.isEmpty():
            return False
        self.tail -= 1
        self.tail += self.total
        self.tail %= self.total
        self.size -= 1
        return True

    def getFront(self) -> int:
        if self.isEmpty():
            return -1
        return self.arr[self.head]

    def getRear(self) -> int:
        if self.isEmpty():
            return -1
        return self.arr[self.tail]

    def isEmpty(self) -> bool:
        return self.size == 0

    def isFull(self) -> bool:
        return self.size == self.total
