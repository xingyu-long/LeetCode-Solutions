from collections import deque
from typing import List


class Solution:
    # time: O(N)
    # space: O(N)
    def continuousSubarrays(self, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)
        res = 0
        start, end = 0, 0
        min_q = deque()
        max_q = deque()

        while end < n:
            while min_q and nums[end] < nums[min_q[-1]]:
                min_q.pop()
            while max_q and nums[end] > nums[max_q[-1]]:
                max_q.pop()

            min_q.append(end)
            max_q.append(end)

            # check the what's inside of window
            while nums[max_q[0]] - nums[min_q[0]] > 2:
                if nums[min_q[0]] == nums[start]:
                    min_q.popleft()
                if nums[max_q[0]] == nums[start]:
                    max_q.popleft()
                start += 1
            res += end - start + 1
            end += 1

        return res
