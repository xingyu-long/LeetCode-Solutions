class Solution:
    def maxWidthRamp(self, nums: List[int]) -> int:
        # n^2 for brute force
        if not nums:
            return 0
        stack = []
        n = len(nums)
        for i in range(n):
            if not stack or nums[i] < nums[stack[-1]]:
                # add decreasing only
                stack.append(i)

        res = 0
        for i in range(n)[::-1]:
            while stack and nums[i] >= nums[stack[-1]]:
                res = max(res, i - stack.pop())
        return res
