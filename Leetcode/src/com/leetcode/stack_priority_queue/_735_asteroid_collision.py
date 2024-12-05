from typing import List


class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        # stack
        stack = []
        for num in asteroids:
            while stack and stack[-1] > 0 and num < 0 and stack[-1] < abs(num):
                stack.pop()

            if not stack or num > 0 or stack[-1] < 0:
                stack.append(num)
            elif num < 0 and stack[-1] == abs(num):
                stack.pop()

        return stack
