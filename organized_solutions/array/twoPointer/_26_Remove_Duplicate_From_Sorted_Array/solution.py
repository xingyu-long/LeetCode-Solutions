'''
Date: 02/06/2022 19:06:07
LastEditTime: 02/06/2022 19:15:56
Description: Two Pointer
'''


from ast import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        res = 0
        for i in range(1, len(nums)):
            if nums[i] != nums[res]:
                res += 1
                nums[res] = nums[i]
        return res + 1
