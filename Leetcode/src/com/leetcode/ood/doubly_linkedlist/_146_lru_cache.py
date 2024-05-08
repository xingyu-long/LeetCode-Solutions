class DNode:
    def __init__(self, key=None, val=None):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.head = DNode()
        self.tail = DNode()
        self.map = dict()
        self.max_capacity = capacity
        self.curr_capacity = 0

        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        node = self.map.get(key, None)
        found = node != None
        if not found:
            return -1

        self.move_to_head(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        node = self.map.get(key, None)
        found = node != None
        if found:
            node.val = value
            self.move_to_head(node)
        else:
            new_node = DNode(key, value)
            self.map[key] = new_node
            self.add_node(new_node)
            self.curr_capacity += 1
            if self.curr_capacity > self.max_capacity:
                self.remove_lru_entry()

    def add_node(self, node: DNode):
        """
        Add new node to doubly linked list
        HEAD -> new_node -> ... -> TAIL
        """
        nxt_node = self.head.next

        self.head.next = node
        node.prev = self.head

        node.next = nxt_node
        nxt_node.prev = node

    def remove_node(self, node: DNode):
        prev_node = node.prev
        nxt_node = node.next

        prev_node.next = nxt_node
        nxt_node.prev = prev_node

    def pop_tail(self):
        prev_node = self.tail.prev
        self.remove_node(prev_node)
        return prev_node

    def move_to_head(self, node: DNode):
        self.remove_node(node)
        self.add_node(node)

    def remove_lru_entry(self):
        node = self.pop_tail()
        del self.map[node.key]
        self.curr_capacity -= 1
