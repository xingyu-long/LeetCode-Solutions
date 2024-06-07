from typing import List


class Solution:
    def sortTransformedArray(
        self, nums: List[int], a: int, b: int, c: int
    ) -> List[int]:
        f = lambda x: a * x * x + b * x + c
        n = len(nums)
        res = [0] * n
        start, end = 0, n - 1
        index = n - 1 if a >= 0 else 0
        while start <= end:
            start_num = f(nums[start])
            end_num = f(nums[end])
            if a >= 0:
                if start_num > end_num:
                    res[index] = start_num
                    start += 1
                else:
                    res[index] = end_num
                    end -= 1
                index -= 1
            else:
                if start_num < end_num:
                    res[index] = start_num
                    start += 1
                else:
                    res[index] = end_num
                    end -= 1
                index += 1

        return res
