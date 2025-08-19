from typing import List


class Solution:
    # time: O(N)
    # space: O(N)
    def sortTransformedArray(
        self, nums: List[int], a: int, b: int, c: int
    ) -> List[int]:
        transform_fn = lambda x: a * x * x + b * x + c
        n = len(nums)
        res = [0] * n
        start, end = 0, n - 1
        index = n - 1 if a >= 0 else 0
        while start <= end:
            start_num = transform_fn(nums[start])
            end_num = transform_fn(nums[end])
            if a >= 0:
                # max(start_num, end_num)
                if start_num > end_num:
                    res[index] = start_num
                    start += 1
                else:
                    res[index] = end_num
                    end -= 1
                index -= 1
            else:
                # min(start_num, end_num)
                if start_num < end_num:
                    res[index] = start_num
                    start += 1
                else:
                    res[index] = end_num
                    end -= 1
                index += 1

        return res
