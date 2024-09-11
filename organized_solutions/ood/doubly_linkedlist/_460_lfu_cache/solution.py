from collections import defaultdict, OrderedDict


class Node:
    def __init__(self, key, val, freq=1):
        self.key = key
        self.val = val
        self.freq = freq


class LFUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.min_count = None

        # key -> Node
        self.m = {}
        # freq -> {key -> node}
        self.freq_to_node = defaultdict(OrderedDict)

    def get(self, key: int) -> int:
        if key not in self.m:
            return -1
        node = self.m[key]

        # clear node with current freq
        del self.freq_to_node[node.freq][key]

        if len(self.freq_to_node[node.freq]) == 0:
            del self.freq_to_node[node.freq]

        node.freq += 1
        self.freq_to_node[node.freq][key] = node
        if len(self.freq_to_node[self.min_count]) == 0:
            self.min_count += 1

        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.m:
            self.m[key].val = value
            self.get(key)
        else:
            if len(self.m) == self.capacity:
                # remove least frequent used element by FIFO
                k, _ = self.freq_to_node[self.min_count].popitem(last=False)
                del self.m[k]

            new_node = Node(key, value)
            self.m[key] = new_node
            self.freq_to_node[1][key] = new_node
            self.min_count = 1
