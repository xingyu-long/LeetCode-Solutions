'''
Date: 01/16/2022 15:21:00
LastEditTime: 01/16/2022 15:25:19
Description: Pointer
'''


from ast import List


class Solution:
    def maxDistToClosest(self, seats: List[int]) -> int:
        placed = []
        for index, value in enumerate(seats):
            if value:
                placed.append(index)
        res = 0
        # Compare with the first one. (may sit left most)
        res = max(res, placed[0])
        for i in range(1, len(placed)):
            res = max(res, (placed[i] - placed[i - 1]) // 2)
        # Compare with the last one (may sit right most)
        res = max(res, (len(seats) - placed[-1] - 1))
        return res

    # Without using extra space.
    def maxDistToClosest2(self, seats: List[int]) -> int:
        last, res = -1, 0
        for index, value in enumerate(seats):
            if value:
                dist = index if last < 0 else (index - last) // 2
                res = max(res, dist)
                last = index
        res = max(res, len(seats) - last - 1)
        return res
