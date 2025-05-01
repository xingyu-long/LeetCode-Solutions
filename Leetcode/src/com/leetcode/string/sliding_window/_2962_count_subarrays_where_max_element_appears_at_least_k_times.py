from typing import List


class Solution:
    # time: O(N)
    # space: O(1)
    def countSubarrays(self, nums: List[int], k: int) -> int:
        # sliding window
        """
        find max first
        [1, 3, 2, 3, 3]
        max = 3

        [1, 3, 2, 3] -> +2 [1, 3, 2, 3] [1, 3, 2, 3, 3]
        [3, 2, 3] -> +2 [3, 2, 3], [3, 2, 3, 3]
        [2, 3] -> 0
        [2, 3, 3] -> 1 [2, 3, 3]
        [3, 3] -> +1 [3, 3]
        """
        max_num = max(nums)
        count = 0
        res = start = end = 0
        n = len(nums)
        while end < n:
            if nums[end] == max_num:
                count += 1
            while count >= k:
                # all subarray after the current end
                # should qualify the requirement
                res += n - end
                if nums[start] == max_num:
                    count -= 1
                start += 1
            end += 1
        return res
