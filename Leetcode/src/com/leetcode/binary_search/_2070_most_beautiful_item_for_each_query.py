from typing import List


class Solution:
    # Time: O(NlogN)
    # Space: O(N)
    def maximumBeauty(self, items: List[List[int]], queries: List[int]) -> List[int]:
        # binary search?
        def find(arr, target, max_beauties):
            left, right = 0, len(arr) - 1
            while left + 1 < right:
                mid = left + (right - left) // 2
                if arr[mid][0] <= target:
                    left = mid
                else:
                    right = mid

            res = 0

            if arr[left][0] <= target:
                res = max(res, max_beauties[left])
            if arr[right][0] <= target:
                res = max(res, max_beauties[right])
            return res

        n = len(items)
        items.sort(key=lambda x: (x[0], x[1]))
        max_beauties = [0] * n
        # show the max beauty we get so far
        for i in range(n):
            max_beauties[i] = max(max_beauties[i - 1] if i > 0 else 0, items[i][1])
        res = []
        for q in queries:
            res.append(find(items, q, max_beauties))

        return res
