from collections import defaultdict
from typing import List


class Solution:
    def countGood(self, nums: List[int], k: int) -> int:
        # subarray
        pairs = 0
        res = 0
        start = end = 0
        n = len(nums)

        counter = defaultdict(int)
        while end < n:
            pairs += counter[nums[end]]
            counter[nums[end]] += 1
            while pairs >= k:
                # from [start, end] and [start,..end..n]
                # they are qualified
                res += n - end
                # this is smart to think of
                counter[nums[start]] -= 1
                pairs -= counter[nums[start]]
                start += 1
            end += 1
        return res
