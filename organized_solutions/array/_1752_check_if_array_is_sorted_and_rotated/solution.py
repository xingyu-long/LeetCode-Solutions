from typing import List


class Solution:
    # 观察得知，对于所有的邻居，只有可能仅有一次是不同的
    # [4,5,1,2,3] -> [5,1] -> True
    # [2,1,3,4] -> [2,1], [4,2] -> False
    def check(self, nums: List[int]) -> bool:
        n = len(nums)
        diff = 0
        for i in range(n):
            nxt = (i + 1) % n
            if nums[i] > nums[nxt]:
                diff += 1
            if diff > 1:
                return False
        return True
