from typing import List
from math import inf


class Solution:
    # time: O(mlogm + nlogn + m + n)
    def findRadius(self, houses: List[int], heaters: List[int]) -> int:
        n = len(heaters)
        houses.sort()
        heaters.sort()
        res = 0
        j = 0
        for house in houses:
            while j + 1 < n and abs(heaters[j + 1] - house) <= abs(heaters[j] - house):
                j += 1

            res = max(res, abs(heaters[j] - house))
        return res


class Solution:
    def findRadius(self, houses: List[int], heaters: List[int]) -> int:

        houses.sort()
        heaters.sort()

        def find(left, right, target):
            # find the index where heaters[index] >= target
            while left + 1 < right:
                mid = left + (right - left) // 2
                if heaters[mid] >= target:
                    right = mid
                else:
                    left = mid
            if heaters[left] >= target:
                return left
            if heaters[right] >= target:
                return right
            return right + 1

        res = 0
        n = len(heaters)
        for house in houses:
            idx = find(0, n - 1, house)
            dist1 = inf if idx == n else heaters[idx] - house
            dist2 = inf if idx == 0 else house - heaters[idx - 1]
            res = max(res, min(dist1, dist2))
        return res
