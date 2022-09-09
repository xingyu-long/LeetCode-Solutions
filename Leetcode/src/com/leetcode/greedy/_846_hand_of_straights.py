from collections import Counter
from typing import List


class Solution:
    # time: O(MlogM + MW), where M is the number of different cards and W is the group size
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        if len(hand) % groupSize:
            return False
        count = Counter(hand)
        for i in sorted(count):
            if count[i] > 0:
                for j in range(groupSize)[::-1]:
                    # why reversed? It won't affect the count[i]
                    count[i + j] -= count[i]
                    if count[i + j] < 0:
                        return False
        return True
