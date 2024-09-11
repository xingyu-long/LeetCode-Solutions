from typing import List


class Solution:
    # sweep line
    # time: O(NlogN + N)
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        if not trips:
            return True

        arr = []
        for p, enter, exit in trips:
            arr.append([enter, p])
            arr.append([exit, -p])

        arr.sort(key=lambda x: (x[0], x[1]))

        count = 0
        for _, num in arr:
            count += num
            if count > capacity:
                return False
        return True
