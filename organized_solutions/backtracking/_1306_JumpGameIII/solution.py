from collections import deque
from typing import List


class Solution:
    # time: O(n)
    # space: O(n)
    def canReach(self, arr: List[int], start: int) -> bool:
        queue = deque()
        n = len(arr)
        visited = set()
        queue.append(start)
        while queue:
            curr = queue.popleft()
            if arr[curr] == 0:
                return True
            if curr not in visited:
                visited.add(curr)
                if curr + arr[curr] < n:
                    queue.append(curr + arr[curr])
                if curr - arr[curr] >= 0:
                    queue.append(curr - arr[curr])
        return False
