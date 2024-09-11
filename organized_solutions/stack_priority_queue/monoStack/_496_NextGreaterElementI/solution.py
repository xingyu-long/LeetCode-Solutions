from typing import List


class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        stack = []
        m = {}
        n = len(nums1)
        for num in nums2:
            while stack and num > stack[-1]:
                prev = stack.pop()
                m[prev] = num
            stack.append(num)

        return [m[num] if num in m else -1 for num in nums1]
