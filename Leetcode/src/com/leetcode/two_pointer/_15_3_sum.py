class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        sorted_nums = sorted(nums)
        n = len(nums)
        res = []
        for i in range(n):
            if i > 0 and sorted_nums[i] == sorted_nums[i - 1]:
                continue
            j, k = i + 1, n - 1
            while j < k:
                total = sorted_nums[i] + sorted_nums[j] + sorted_nums[k]
                if total == 0:
                    res.append([sorted_nums[i], sorted_nums[j], sorted_nums[k]])
                    # remove same pairs
                    while j < k and sorted_nums[j] == sorted_nums[j + 1]:
                        j += 1
                    while j < k and sorted_nums[k] == sorted_nums[k - 1]:
                        k -= 1
                    j, k = j + 1, k - 1
                elif total > 0:
                    k -= 1
                else:
                    j += 1
        return res
