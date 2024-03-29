'''
Date: 08/28/2022 17:21:54
LastEditTime: 08/28/2022 17:36:36
Description: Binary Search
'''
from typing import List


class Solution:
    """
    Solution:
    一开始想到的是利用heap来构建关系，但是发现
    行不通，只能利用二分搜索去猜这个最小的差值是多少
    去找最小的绝对值差值，其能够构成的pair数目大于等于k
    """
    # time:O(log(maxDiff) * nlogn (find right bound) + nlogn(sorting) )
    def smallestDistancePair(self, nums: List[int], k: int) -> int:
        """
        Returns number of pairs with absolute difference less than or equal to mid.
        """
        def count_pairs(nums, mid):
            count = 0
            for i in range(len(nums)):
                j = i
                # it can be optimized by binary search
                while j < len(nums) and nums[j] - nums[i] <= mid:
                    j += 1
                count += j - i - 1
            return count

        def find_right_bound(nums, prev_idx, target):
            left, right = prev_idx, len(nums) - 1
            while left + 1 < right:
                mid = left + (right - left) // 2
                if nums[mid] - nums[prev_idx] <= target:
                    left = mid
                else:
                    right = mid
            if nums[right] - nums[prev_idx] <= target:
                return right
            if nums[left] - nums[prev_idx] <= target:
                return left
            return -1

        def count_pairs_with_binary_search(nums, mid):
            count = 0
            for i in range(len(nums)):
                right_idx = find_right_bound(nums, i, mid)
                count += right_idx - i
            return count

        nums.sort()
        left, right = 0, nums[-1] - nums[0]
        while left + 1 < right:
            mid = left + (right - left) // 2
            if count_pairs_with_binary_search(nums, mid) < k:
                left = mid
            else:
                right = mid

        if count_pairs_with_binary_search(nums, left) >= k:
            return left
        return right
