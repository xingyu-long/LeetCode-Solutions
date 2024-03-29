'''
Date: 08/06/2022 11:43:41
LastEditTime: 08/08/2022 14:26:05
Description: You need to fill out
'''
from collections import defaultdict
from itertools import accumulate
from re import I


class Solution:
    """
    https://leetcode.com/problems/sum-of-total-strength-of-wizards/discuss/2062017/C%2B%2B-prefix-%2B-monotonic-stack-O(N)-solution-with-thought-process
    解题思路：
    利用单调栈分别向前和向后找到下一个比当前值小的情况(类似于907),
    这样的话，在这个区间里面只有当前的值是最小的。可以贡献给当前的各种子区间。
    上面对于 sum of all subarrays including strength[i] in range (left, right)的解释
    the subarrays that start with left+1
        sum(left+1, ...i) = prefix[i + 1] - prefix[left + 1]
        sum(left+1, ... i+1) = prefix[i + 2] - prefix[left + 1]
        ...
        sum(left+1, ... right-1) = prefix[right] - prefix[left + 1]
    the subarrays that start with left+2
        sum(left+2, ... i) = prefix[i + 1] - prefix[left + 2]
        sum(left+2, ... i+1) = prefix[i + 2] - prefix[left + 2]
        ...
        sum(left+2, ... right-1) = prefix[right] - prefix[left + 2]
    以i作为分界点(left.... i, ....right)
    只看上面等式前半部分（正数的部分），会发现有(i - left)个(prefix[i + 1] + prefix[i + 2] + ...prefix[right])
    后半部分则是（被减去的部分）(prefix[left + 1] + prefix[left + 2] + ... + prefix[i]) * (right - i)
    """

    def totalStrength(self, nums: List[int]) -> int:
        if not nums:
            return 0
        res = 0
        prev_small = defaultdict(lambda: -1)
        next_small_or_equals = defaultdict(lambda: len(nums))
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
        prefix_prefix_sum = list(accumulate(accumulate(nums), initial=0))
        for idx, num in enumerate(nums):
            prev_s = prev_small[idx]
            next_s = next_small_or_equals[idx]
            left_prefix_prefix = prefix_prefix_sum[idx] - \
                prefix_prefix_sum[max(prev_s, 0)]
            right_prefix_prefix = prefix_prefix_sum[next_s] - \
                prefix_prefix_sum[idx]
            left_n, right_n = idx - prev_s, next_s - idx
            prod = (right_prefix_prefix * left_n -
                    left_prefix_prefix * right_n) % (10**9 + 7)
            res = (res + nums[idx] * prod) % (10**9 + 7)

        return res
