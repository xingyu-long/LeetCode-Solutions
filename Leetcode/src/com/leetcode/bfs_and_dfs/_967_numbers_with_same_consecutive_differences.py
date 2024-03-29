'''
Date: 09/03/2022 10:50:53
LastEditTime: 09/03/2022 11:07:52
Description: DFS, BFS
'''
from collections import deque
from typing import List


class Solution:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        global res
        res = []

        def dfs(curr, n, k):
            global res
            if n < 0:
                return
            if n == 0:
                res.append(curr)

            last_digit = curr % 10
            if last_digit + k < 10:
                dfs(curr * 10 + (last_digit + k), n - 1, k)
            if k != 0 and last_digit - k >= 0:
                dfs(curr * 10 + (last_digit - k), n - 1, k)

        for i in range(1, 10):
            dfs(i, n - 1, k)

        return res

    # If K >= 5, time and Space O(N)
    # If K <= 4, time and space O(2^N)
    def numsSameConsecDiff2(self, n: int, k: int) -> List[int]:
        res = []
        queue = deque()
        for i in range(1, 10):
            queue.append(i)
        n -= 1
        while queue and n >= 0:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                if n == 0:
                    res.append(curr)
                last_digit = curr % 10
                if last_digit + k < 10:
                    queue.append(curr * 10 + (last_digit + k))
                if k != 0 and last_digit >= k:
                    queue.append(curr * 10 + (last_digit - k))
            n -= 1

        return res
