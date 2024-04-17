from typing import List


class Solution:
    # time: O(n) space: O(1)
    def findDuplicate(self, nums: List[int]) -> int:
        if not nums:
            return -1
        slow = nums[0]
        fast = nums[slow]
        while slow != fast:
            slow = nums[slow]
            fast = nums[nums[fast]]
        slow = 0
        while slow != fast:
            slow = nums[slow]
            fast = nums[fast]
        return slow

class Solution2:
    # mark it visited
    # time: O(n) space: O(1)
    def findDuplicate(self, nums: List[int]) -> int:
        n = len(nums)
        for num in nums:
            idx = abs(num)
            if nums[idx] < 0:
                return idx
            nums[idx] = -nums[idx]
        return n

class Solution3:
    # TODO: binary search
    pass