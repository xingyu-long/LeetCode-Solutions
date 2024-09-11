from collections import deque
from typing import List


class Solution:
    """
    解题思路：
    利用单调队列始终保持头部是最大或者最小，利用两个的差值判断
    当前的区间是否小于当前的limit，如果大于的话就说明需要移动
    start的位置并且需要删除在两个队列里的对应位置
    """

    # time: O(n) space: O(n)
    def longestSubarray(self, nums: List[int], limit: int) -> int:
        # min, max of sub array <= limit
        n = len(nums)
        res = 0
        start, end = 0, 0
        min_queue = deque()
        max_queue = deque()
        while end < n:
            while min_queue and nums[end] < nums[min_queue[-1]]:
                min_queue.pop()
            min_queue.append(end)

            while max_queue and nums[end] > nums[max_queue[-1]]:
                max_queue.pop()
            max_queue.append(end)

            while nums[max_queue[0]] - nums[min_queue[0]] > limit:
                if nums[max_queue[0]] == nums[start]:
                    max_queue.popleft()
                if nums[min_queue[0]] == nums[start]:
                    min_queue.popleft()
                start += 1

            res = max(res, end - start + 1)
            end += 1
        return res
