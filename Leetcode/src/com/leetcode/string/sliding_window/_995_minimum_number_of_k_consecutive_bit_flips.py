from collections import deque
from typing import List


class Solution:
    # time: O(n)
    # space: O(k)
    # https://www.youtube.com/watch?v=Fv3M9uO5ovU
    def minKBitFlips(self, nums: List[int], k: int) -> int:
        # use queue to record where we do the flip
        # everything in queue flip the current i since it's in that window range
        queue = deque()
        n = len(nums)
        res = 0
        for i in range(n):
            while queue and i >= queue[0] + k:
                queue.popleft()

            # (nums[i] + len(queue)) % 2: what's the status for i now after a few
            # flips (we recorded it at queue)
            if (nums[i] + len(queue)) % 2 == 0:
                if i + k > n:
                    # we don't have enough elements to flip
                    # eg: [1,1,0]
                    return -1
                res += 1
                queue.append(i)
        return res
