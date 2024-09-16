from typing import List


"""
corner case:
["12:12","00:13"]

"""


class Solution:
    # time: O(NlogN)
    def findMinDifference(self, timePoints: List[str]) -> int:
        def convert(a):
            hours, mins = a.split(":")
            hours, mins = int(hours), int(mins)
            return hours * 60 + mins

        def cal_diff(a, b):
            return abs(convert(a) - convert(b))

        n = len(timePoints)
        # sort
        timePoints.sort(key=lambda x: convert(x))

        res = float("inf")
        for i in range(1, n):
            diff = cal_diff(timePoints[i], timePoints[i - 1])
            res = min(res, diff, 24 * 60 - diff)

        # image it as the circle
        last_diff = cal_diff(timePoints[n - 1], timePoints[0])
        res = min(res, last_diff, 24 * 60 - last_diff)
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
