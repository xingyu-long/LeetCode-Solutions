from typing import List


"""
42. Trapping Rain Water
---

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
---

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9
"""


class Solution:
    # time: O(n)
    # space: O(1)
    def trap(self, height: List[int]) -> int:
        n = len(height)
        left, right = 0, n - 1
        left_max, right_max = 0, 0
        res = 0
        while left < right:
            left_h, right_h = height[left], height[right]
            if left_h <= right_h:
                left_max = max(left_max, height[left])
                res += left_max - height[left]
                left += 1
            else:
                right_max = max(right_max, height[right])
                res += right_max - height[right]
                right -= 1
        return res


class Solution2:
    """
    heights = [0,1,0,2,1,0,1,3,2,1,2,1]
    i = 0, h = 0, stack = [0]
    i = 1, h = 1, stack = [1] => [01] 没法存水 water += 0
    i = 2, h = 0, stack = [1, 2]
    i = 3, h = 2,
        pop idx=2, stack = [1], min_h=min(1,2)=1
        [102] 存水 water += 1
        pop idx=1, stack = [] 没法存水 water += 0
        stack=[3]
    i = 4, h = 1, stack=[3,4]
    i = 5, h = 0, stack=[3,4,5]
    i = 6, h = 1,
        pop idx=5, min_h=1, [101] 存水 water += 1
        stack = [3,4]
    i = 7, h = 3
        pop idx=4, min_h=2, [21013] 存水 water += 3

                |
        | x x x |
        | | _ | |
    ...
    """

    # monotonic stack (keep them decresing)
    def trap(self, height: List[int]) -> int:
        stack = []
        res = 0
        n = len(height)
        for i in range(n):
            while stack and height[i] > height[stack[-1]]:
                prev = stack.pop()
                # there is no left wall
                if not stack:
                    break
                min_h = min(height[stack[-1]], height[i])
                res += (min_h - height[prev]) * (i - stack[-1] - 1)

            stack.append(i)
        return res
