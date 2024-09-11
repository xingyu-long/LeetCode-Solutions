from math import inf
from typing import Counter, List


class Solution:
    def findShortestSubArray(self, nums: List[int]) -> int:
        # sliding window?
        c = Counter(nums)
        freq = max(c.values())
        res = inf
        start, end = 0, 0
        n = len(nums)
        counter = Counter()
        while end < n:
            counter[nums[end]] += 1
            while counter[nums[end]] == freq:
                res = min(res, end - start + 1)
                counter[nums[start]] -= 1
                start += 1
            end += 1
        return res


class Solution2:
    # hash table
    def findShortestSubArray(self, nums: List[int]) -> int:
        m = Counter()
        first = {}
        degree, res = 0, 0
        for i, num in enumerate(nums):
            if num not in first:
                first[num] = i
            m[num] += 1
            if m[num] > degree:
                degree = m[num]
                res = i - first[num] + 1
            elif m[num] == degree:
                res = min(res, i - first[num] + 1)

        return res
