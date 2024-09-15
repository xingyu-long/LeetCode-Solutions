from typing import List


# hint: Notice that the bitwise AND of two different numbers will
# always be strictly less than the maximum of those two numbers.
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        max_num = max(nums)
        res = count = 0
        for num in nums:
            if num == max_num:
                count += 1
                res = max(res, count)
            else:
                count = 0

        return res


class Solution2:
    # one pass
    def longestSubarray(self, nums: List[int]) -> int:
        max_num = 0
        res = count = 0
        for num in nums:
            if num == max_num:
                count += 1
                res = max(res, count)
            elif num > max_num:
                max_num = num
                res = count = 1
            else:
                count = 0

        return res
