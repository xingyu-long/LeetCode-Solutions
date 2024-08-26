from typing import List


class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:

        def count_miss(idx):
            # for idx, the default num should be idx+1 and the gap would be arr[idx] - (idx + 1)
            return arr[idx] - (idx + 1)

        left, right = 0, len(arr) - 1
        while left <= right:
            mid = left + (right - left) // 2
            count = count_miss(mid)
            if count >= k:
                right = mid - 1
            else:
                left = mid + 1

        return left + k
