'''
Date: 08/18/2022 16:30:00
LastEditTime: 08/18/2022 16:32:22
Description: Mono Deque
'''
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
        min_deque = deque()
        max_deque = deque()
        res, start = 0, 0
        for idx, num in enumerate(nums):
            while len(min_deque) and num < nums[min_deque[-1]]:
                min_deque.pop()
            min_deque.append(idx)

            while len(max_deque) and num > nums[max_deque[-1]]:
                max_deque.pop()
            max_deque.append(idx)

            while nums[max_deque[0]] - nums[min_deque[0]] > limit:
                if nums[max_deque[0]] == nums[start]:
                    max_deque.popleft()
                if nums[min_deque[0]] == nums[start]:
                    min_deque.popleft()
                start += 1

            res = max(res, idx - start + 1)

        return res
