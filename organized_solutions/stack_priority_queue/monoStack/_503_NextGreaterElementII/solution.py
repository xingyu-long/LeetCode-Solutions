from typing import List


class Solution:
    def nextGreaterElements(self, nums: List[int]) -> List[int]:
        stack = []
        n = len(nums)
        res = [-1] * n
        for i in range(2 * n):
            idx = i % n
            while stack and nums[idx] > nums[stack[-1]]:
                prev_idx = stack.pop()
                if res[prev_idx] == -1:
                    res[prev_idx] = nums[idx]
            stack.append(idx)
        return res
