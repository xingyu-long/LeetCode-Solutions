'''
Date: 04/20/2022 20:43:13
LastEditTime: 04/20/2022 20:43:13
Description: Multiplicative Hash
'''


class MyHashSet:

    def __init__(self):
        self.array = [[] for _ in range(1 << 15)]

    def compute_hash(self, key):
        return ((key*1031237) & (1 << 20) - 1) >> 5

    def add(self, key: int) -> None:
        hash_value = self.compute_hash(key)
        if key not in self.array[hash_value]:
            self.array[hash_value].append(key)

    def remove(self, key: int) -> None:
        hash_value = self.compute_hash(key)
        if key in self.array[hash_value]:
            self.array[hash_value].remove(key)

    def contains(self, key: int) -> bool:
        hash_value = self.compute_hash(key)
        return key in self.array[hash_value]
