from typing import List


class Solution:
    # quick select
    # time: O(nlogn) -> on average O(n)
    def findKthLargest(self, nums: List[int], k: int) -> int:
        def exch(nums: List[int], i: int, j: int) -> None:
            nums[i], nums[j] = nums[j], nums[i]

        def partition(nums: List[int], left: int, right: int) -> int:
            start, end = left, right - 1
            pivot = nums[right]
            while start <= end:
                if nums[start] < pivot:
                    start += 1
                elif nums[end] > pivot:
                    end -= 1
                else:
                    exch(nums, start, end)
                    start += 1
                    end -= 1
            exch(nums, start, right)
            return start

        n = len(nums)
        k = n - k
        left, right = 0, n - 1
        while left < right:
            j = partition(nums, left, right)
            if j == k:
                break
            elif j < k:
                left = j + 1
            else:
                right = j - 1
        return nums[k]
