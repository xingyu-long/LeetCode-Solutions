import unittest
from typing import List


class Solution:
    # time: O(n^2)
    # space: O(n)
    def minimumMountainRemovals(self, nums: List[int]) -> int:
        n = len(nums)
        forwards = [0] * n
        backwards = [0] * n
        for i in range(n):
            max_len = 1
            for j in range(i):
                if nums[j] < nums[i]:
                    max_len = max(max_len, forwards[j] + 1)
            forwards[i] = max_len
        for i in range(n)[::-1]:
            max_len = 1
            for j in range(i, n):
                if nums[j] < nums[i]:
                    max_len = max(max_len, backwards[j] + 1)
            backwards[i] = max_len

        longest_count = 0
        for c1, c2 in zip(forwards, backwards):
            # so we have least 1 elements on both side
            if c1 >= 2 and c2 >= 2:
                longest_count = max(longest_count, c1 + c2)

        return n - (longest_count - 1)


class TestCase(unittest.TestCase):

    def setUp(self):
        self.fn = Solution().minimumMountainRemovals

    def test_examples(self):
        self.assertEqual(self.fn([1, 3, 1]), 0)
        self.assertEqual(self.fn([1, 2, 3, 4, 5, 1]), 0)
        self.assertEqual(self.fn([2, 1, 1, 5, 6, 2, 3, 1]), 3)
        self.assertEqual(self.fn([9, 8, 1, 7, 6, 5, 4, 3, 2, 1]), 2)


if __name__ == "__main__":
    unittest.main()
