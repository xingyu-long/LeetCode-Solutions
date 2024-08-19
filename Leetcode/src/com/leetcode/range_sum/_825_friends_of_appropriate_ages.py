from collections import Counter
from typing import List


class Solution:
    def numFriendRequests(self, ages: List[int]) -> int:
        if not ages:
            return 0

        def is_valid(a, b):
            return not (a * 0.5 + 7 >= b or b > a)

        counters = Counter(ages)
        res = 0
        for a, num_a in counters.items():
            for b, num_b in counters.items():
                if is_valid(a, b):
                    res += num_a * num_b
                    if a == b:
                        # exclude me requesting to myself
                        res -= num_a
        return res
