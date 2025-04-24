from typing import List
from collections import defaultdict

class Solution:
    def countCompleteSubarrays(self, nums: List[int]) -> int:
        n = len(nums)
        start, end = 0, 0
        res = 0
        counter = defaultdict(int)
        k = len(set(nums))
        while end < n:
            counter[nums[end]] += 1
            while len(counter) == k:
                # a, b, c, ... end .x, y, z
                # so anything after end are also qualified for the subarray 
                res += n - end
                counter[nums[start]] -= 1
                if counter[nums[start]] == 0:
                    del counter[nums[start]]
                start += 1
            end += 1
        return res
