from typing import List


class Solution:
    # time: O(logN)
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        def can_eat_all(k: int) -> bool:
            count = 0
            for p in piles:
                count += p // k
                if p % k != 0:
                    count += 1
            return count <= h

        left, right = 1, 10**9 + 1
        while left + 1 < right:
            mid = left + (right - left) // 2
            if can_eat_all(mid):
                right = mid
            else:
                left = mid

        if can_eat_all(left):
            return left
        if can_eat_all(right):
            return right
        return -1
