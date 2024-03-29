from typing import List


class Solution:
    """
    Solution:
    找出每一个数组里面的最大最小值，然后与当前的最大最小值做比较即可

    Time: O(n)
    Space: O(1)
    """
    def maxDistance(self, arrays: List[List[int]]) -> int:
        mi, mx = arrays[0][0], arrays[0][-1]
        res = 0
        for i in range(1, len(arrays)):
            for curr in (arrays[i][0], arrays[i][-1]):
                res = max(res, abs(curr - mi))
                res = max(res, abs(mx - curr))
            mi = min(mi, arrays[i][0])
            mx = max(mx, arrays[i][-1])
        return res
