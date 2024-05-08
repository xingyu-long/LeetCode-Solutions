from typing import List


class Solution:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        pairs = [(pos, v) for pos, v in zip(position, speed)]
        stack = []
        # they will be sorted by position
        for pos, v in sorted(pairs, reverse=True):
            remain = (target - pos) / v
            if (not stack) or (stack and remain > stack[-1]):
                # for 2nd condition, it means this one needs more time and cannot form
                # as car fleet with stack[-1]
                stack.append(remain)
        return len(stack)
