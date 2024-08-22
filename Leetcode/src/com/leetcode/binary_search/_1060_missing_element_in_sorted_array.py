class Solution:
    def missingElement(self, nums: List[int], k: int) -> int:

        def count_miss(i, j):
            return nums[j] - nums[i] - (j - i)

        left, right = 0, len(nums) - 1
        num_miss = count_miss(left, right)
        if num_miss < k:
            return nums[right] + k - num_miss
        while left + 1 < right:
            mid = left + (right - left) // 2
            num_miss = count_miss(left, mid)
            if num_miss >= k:
                right = mid
            else:
                left = mid
                k -= num_miss
        return nums[left] + k
