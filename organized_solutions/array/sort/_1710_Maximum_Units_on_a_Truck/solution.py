'''
Date: 08/09/2022 22:00:45
LastEditTime: 08/09/2022 22:00:45
Description: Sort
'''
from typing import List


class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        boxTypes.sort(key=lambda x: x[1], reverse=True)
        res = 0
        for count, unit in boxTypes:
            if truckSize == 0:
                break
            min_count = min(count, truckSize)
            res += min_count * unit
            truckSize -= min_count
        return res
