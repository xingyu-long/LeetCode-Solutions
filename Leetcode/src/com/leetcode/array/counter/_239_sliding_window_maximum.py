from collections import deque
from typing import List


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        # we just need to store idx
        queue = deque()
        res = []
        for i in range(n):
            # don't forgot this!!!
            while queue and i - queue[0] + 1 > k:
                queue.popleft()
            # keep pop if nums[i] is bigger
            while queue and nums[i] >= nums[queue[-1]]:
                queue.pop()

            queue.append(i)

            if i >= k - 1:
                res.append(nums[queue[0]])
        return res
