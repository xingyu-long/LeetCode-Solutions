class Block:
    def __init__(self, freq=0):
        self.freq = freq
        self.keys = set()
        self.prev = None
        self.next = None

    def remove(self):
        # remove itself
        prev, nxt = self.prev, self.next
        prev.next = nxt
        nxt.prev = prev

    def insert_after(self, new_block):
        """
        [head] -> ... -> [current block] -> [new block] ... ->[tail]
        """
        tmp = self.next
        self.next = new_block
        new_block.prev = self

        new_block.next = tmp
        tmp.prev = new_block

    def __repr__(self):
        return f"Block(freq={self.freq})"


class AllOne:

    def __init__(self):
        self.head = Block()
        self.tail = Block()

        self.head.next = self.tail
        self.tail.prev = self.head

        # key -> block
        self.m = {}

    def inc(self, key: str) -> None:
        if key not in self.m:
            current_block = self.head
        else:
            current_block = self.m[key]
            current_block.keys.remove(key)

        # check if we have next block (freq will be increased by 1)
        if current_block.freq + 1 != current_block.next.freq:
            new_block = Block(current_block.freq + 1)
            current_block.insert_after(new_block)
        else:
            new_block = current_block.next

        new_block.keys.add(key)
        self.m[key] = new_block

        # if block is empty and its not head or tail, let's remove it
        if not current_block.keys and current_block.freq != 0:
            current_block.remove()

    def dec(self, key: str) -> None:
        if key not in self.m:
            return

        current_block = self.m[key]
        current_block.keys.remove(key)
        # we need to remove key from self.m
        del self.m[key]

        # if freq == 1, we will put things in self.head which is unexpected.
        if current_block.freq != 1:
            if current_block.freq - 1 != current_block.prev.freq:
                new_block = Block(current_block.freq - 1)
                current_block.prev.insert_after(new_block)
            else:
                new_block = current_block.prev

            new_block.keys.add(key)
            self.m[key] = new_block

        if not current_block.keys:
            current_block.remove()

    def getMaxKey(self) -> str:
        if self.tail.prev.freq == 0:
            return ""
        key = self.tail.prev.keys.pop()
        self.tail.prev.keys.add(key)
        return key

    def getMinKey(self) -> str:
        if self.head.next.freq == 0:
            return ""
        key = self.head.next.keys.pop()
        self.head.next.keys.add(key)
        return key
