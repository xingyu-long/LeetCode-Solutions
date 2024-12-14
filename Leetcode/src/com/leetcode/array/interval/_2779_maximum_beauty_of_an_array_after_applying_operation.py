from typing import List


class Solution:
    def maximumBeauty(self, nums: List[int], k: int) -> int:
        """
        4 -> [2, 6]
        6 -> [4, 8]
        1 -> [-1, 3]
        2 -> [0, 4]
        """
        # interval problem
        arr = []
        for num in nums:
            arr.append((num - k, 1))
            arr.append((num + k, -1))
        # sort by position and enter first
        arr.sort(key=lambda x: (x[0], -x[1]))
        res = count = 0
        for _, enter in arr:
            count += enter
            res = max(res, count)
        return res


class Solution2:
    # sliding window
    def maximumBeauty(self, nums: List[int], k: int) -> int:
        n = len(nums)
        # why sorting won't affect the order?
        nums.sort()
        start, end = 0, 0
        res = 0
        while end < n:
            # maintain the window for from start to end which
            # the diff is smaller than 2 * k, so they all can
            # pick common num.
            while nums[end] - nums[start] > 2 * k:
                start += 1
            res = max(res, end - start + 1)
            end += 1
        return res
