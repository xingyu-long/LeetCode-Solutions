from collections import defaultdict


class TimeMap:

    def __init__(self):
        # key->{timestamp->value}
        self.data = defaultdict(list)

    def search(self, key: str, timestamp: int) -> str:
        left, right = 0, len(self.data[key]) - 1
        data = self.data[key]
        while left + 1 < right:
            mid = left + (right - left) // 2
            if timestamp == data[mid][0]:
                return data[mid][1]
            elif timestamp > data[mid][0]:
                left = mid
            else:
                right = mid
        if 0 <= right < len(data) and data[right][0] <= timestamp:
            return data[right][1]
        if 0 <= left < len(data) and data[left][0] <= timestamp:
            return data[left][1]
        return ""

    def set(self, key: str, value: str, timestamp: int) -> None:
        # since timestamp is strictly increasing, so we don't need the heap here.
        self.data[key].append((timestamp, value))

    def get(self, key: str, timestamp: int) -> str:
        return self.search(key, timestamp)


# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)
