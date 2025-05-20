from typing import List


class Solution:
    # sweep line
    # time: O(N)
    # space: O(N)
    def isZeroArray(self, nums: List[int], queries: List[List[int]]) -> bool:
        # prefix sum
        n = len(nums)
        freq = [0] * (n + 1)
        for s, e in queries:
            freq[s] += 1
            freq[e + 1] -= 1

        prefix = 0
        for i in range(n):
            prefix += freq[i]
            # we want the prefix can be bigger than nums[i]
            # if max(0, nums[i] - prefix) > 0:
            if nums[i] > prefix:
                return False
        return True
