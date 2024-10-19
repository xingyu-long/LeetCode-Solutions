from typing import List

"""
corner case:
["12:12","00:13"]

"""


class Solution:
    def findMinDifference(self, timePoints: List[str]) -> int:

        def compute_time(s: str) -> int:
            hr, mins = s.split(":")
            hr, mins = int(hr), int(mins)
            return hr * 60 + mins

        n = len(timePoints)
        timePoints.sort(key=lambda x: compute_time(x))
        res = float("inf")
        for i in range(n):
            prev = i - 1
            if prev < 0:
                prev += n
            # image this as circle, we also need to compare the last
            # one with the first one.
            t1, t2 = map(compute_time, (timePoints[prev], timePoints[i]))
            res = min(res, abs(t1 - t2), 24 * 60 - abs(t1 - t2))

        return res


class Solution2:
    def findMinDifference(self, timePoints: List[str]) -> int:
        mark = [False] * (24 * 60)
        left, right = float("inf"), float("-inf")
        for t in timePoints:
            hours, mins = t.split(":")
            hours, mins = int(hours), int(mins)
            total = hours * 60 + mins
            if mark[total]:
                # we have overlapped time
                return 0

            left = min(left, total)
            right = max(right, total)

            mark[total] = True

        res = float("inf")
        first, last = float("inf"), float("-inf")
        prev = 0
        for i in range(left, right + 1):
            if mark[i]:
                if first != float("inf"):
                    res = min(res, i - prev)

                first = min(first, i)
                last = max(last, i)
                prev = i

        res = min(res, 24 * 60 - (last - first))
        return res
