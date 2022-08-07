'''
Date: 08/06/2022 11:43:41
LastEditTime: 08/06/2022 11:43:42
Description: You need to fill out
'''
from collections import defaultdict


class Solution:
    def totalStrength(self, nums: List[int]) -> int:
        if not nums:
            return 0
        res = 0
        prev_small = defaultdict(lambda: -1)
        next_small_or_equals = defaultdict(lambda: len(nums))
        prefix = [0 for _ in range(len(nums) + 1)]
        for i in range(1, len(nums) + 1):
            prefix[i] = prefix[i - 1] + nums[i - 1]
        stack = []
        for idx, num in enumerate(nums):
            while len(stack) and num <= nums[stack[-1]]:
                next_small_or_equals[stack[-1]] = idx
                stack.pop()
            stack.append(idx)
        stack.clear()
        for idx, num in reversed(list(enumerate(nums))):
            while len(stack) and num < nums[stack[-1]]:
                prev_small[stack[-1]] = idx
                stack.pop()
            stack.append(idx)
        for idx, num in enumerate(nums):
            prev_s = prev_small[idx]
            next_s = next_small_or_equals[idx]
            # Where we need to optimize
            for i in range(prev_s + 1, idx + 1):
                for j in range(idx + 1, next_s + 1):
                    prod = num * (prefix[j] - prefix[i]) % (10**9 + 7)
                    res = (res + prod) % (10**9 + 7)
        return res
