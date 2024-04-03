from typing import List


class Solution:
    def jump(self, nums: List[int]) -> int:
        idx, n = 0, len(nums)
        if n <= 1:
            return 0
        can_reach, max_reach = 0, 0
        res = 0
        while can_reach >= idx:
            max_reach = max(max_reach, idx + nums[idx])
            if idx == can_reach:
                res += 1
                can_reach = max_reach
                if can_reach >= n - 1:
                    return res
            idx += 1
        return -1
