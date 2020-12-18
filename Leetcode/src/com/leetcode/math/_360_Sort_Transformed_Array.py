'''
Date: 12/15/2020 10:21:24
LastEditTime: 12/15/2020 10:21:38
Description: Math, Two pointer
'''


class Solution:
    def sortTransformedArray(self, nums: List[int], a: int, b: int, c: int) -> List[int]:
        if not nums:
            return None
        start, end = 0, len(nums) - 1
        res = [0 for _ in range(len(nums))]
        index = len(nums) - 1 if a >= 0 else 0
        while start <= end:
            start_num = self.calculate(nums[start], a, b, c)
            end_num = self.calculate(nums[end], a, b, c)
            if a >= 0:
                if start_num > end_num:
                    res[index] = start_num
                    index -= 1
                    start += 1
                else:
                    res[index] = end_num
                    index -= 1
                    end -= 1
            else:
                if start_num > end_num:
                    res[index] = end_num
                    index += 1
                    end -= 1
                else:
                    res[index] = start_num
                    index += 1
                    start += 1
        return res

    def calculate(self, x, a, b, c):
        return a * x**2 + b * x + c
