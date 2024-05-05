# The knows API is already defined for you.
# return a bool, whether a knows b
# def knows(a: int, b: int) -> bool:


class Solution:
    # time: O(n)
    def findCelebrity(self, n: int) -> int:
        # fake the API call
        def knows(i, j):
            return True

        if n < 2:
            return -1
        res = 0
        for i in range(1, n):
            if knows(res, i):
                res = i
        for i in range(n):
            if i == res:
                continue
            if knows(res, i) or not knows(i, res):
                return -1
        return res
