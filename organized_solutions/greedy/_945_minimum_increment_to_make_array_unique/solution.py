from typing import List


class Solution:
    # time: O(nlogn)
    def minIncrementForUnique(self, nums: List[int]) -> int:
        if not nums or len(nums) < 2:
            return 0
        res = 0
        n = len(nums)
        nums.sort()
        for i in range(1, n):
            # 对于每一个nums[i]，我们期待的是1）本来就大于前面的数 2）或者是我们把它变成nums[i-1]+1
            if nums[i] <= nums[i - 1]:
                diff = nums[i - 1] + 1 - nums[i]
                nums[i] += diff
                res += diff

        return res
