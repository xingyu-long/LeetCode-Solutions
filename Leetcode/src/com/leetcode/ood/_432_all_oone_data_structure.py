class Block:
    def __init__(self, freq: int = 0):
        self.freq = freq
        self.keys = set()
        self.prev = None
        self.next = None

    def insert(self, block: "Block"):
        # insert it after the self
        nxt = self.next

        block.next = nxt
        nxt.prev = block

        self.next = block
        block.prev = self

    def insert_before(self, block: "Block"):
        prev = self.prev

        prev.next = block
        block.prev = prev

        block.next = self
        self.prev = block

    def delete(self):
        # delete self from the doubly linked list
        prev = self.prev
        nxt = self.next

        prev.next = nxt
        nxt.prev = prev

    def __repr__(self):
        return f"Block[freq={self.freq}, keys={self.keys}]"


class AllOne:
    def __init__(self):
        # key -> block
        self.mp = dict()

        self.head = Block()
        self.tail = Block()
        self.head.next = self.tail
        self.tail.prev = self.head

    def inc(self, key: str) -> None:
        # need to handle the case like inc(a), inc(b), the 2nd one should start from head
        if key not in self.mp:
            block = self.head
        else:
            block = self.mp[key]
            block.keys.remove(key)

        if block.next != self.tail and block.freq + 1 == block.next.freq:
            next_block = block.next
        else:
            next_block = Block(block.freq + 1)
            block.insert(next_block)

        next_block.keys.add(key)
        self.mp[key] = next_block

        # clean up the block if no keys in it
        # and also make sure this is not HEAD
        if not block.keys and block != self.head:
            block.delete()

    def dec(self, key: str) -> None:
        block = self.mp[key]
        block.keys.remove(key)
        del self.mp[key]

        if block.freq > 1:
            # we will try to find/create the prev
            # there is no point to do so for block.freq = 1 since we don't need block with freq = 0
            if block.prev != self.head and block.prev.freq + 1 == block.freq:
                prev_block = block.prev
            else:
                # create one with small freq
                prev_block = Block(block.freq - 1)
                block.insert_before(prev_block)

            prev_block.keys.add(key)
            self.mp[key] = prev_block

        if not block.keys:
            block.delete()

    def getMaxKey(self) -> str:
        max_block = self.tail.prev
        if max_block == self.head:
            return ""
        val = next(iter(max_block.keys))
        return val

    def getMinKey(self) -> str:
        min_block = self.head.next
        if min_block == self.tail:
            return ""
        val = next(iter(min_block.keys))
        return val
