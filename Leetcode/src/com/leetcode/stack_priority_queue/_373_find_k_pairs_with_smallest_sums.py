from heapq import heappop, heappush
from typing import List


class Solution:
    # time: O(KlogK)
    # space: O(K)
    def kSmallestPairs(
        self, nums1: List[int], nums2: List[int], k: int
    ) -> List[List[int]]:
        n1, n2 = len(nums1), len(nums2)
        heap = []

        # add k items from nums1
        for i in range(min(k, n1)):
            # sum, i, j
            heappush(heap, (nums1[i] + nums2[0], i, 0))

        res = []
        while heap and len(res) < k:
            _, i, j = heappop(heap)
            if j + 1 < n2:
                # this means we can use i with j + 1, so we can get the next small
                heappush(heap, (nums1[i] + nums2[j + 1], i, j + 1))

            res.append((nums1[i], nums2[j]))

        return res


class Solution2:
    def kSmallestPairs(
        self, nums1: List[int], nums2: List[int], k: int
    ) -> List[List[int]]:
        n1, n2 = len(nums1), len(nums2)
        heap = []
        res = []
        # we need this to avoid duplicates
        # because these two options can end up with same new_i, new_j
        visited = set()

        heappush(heap, (nums1[0] + nums2[0], 0, 0))
        visited.add((0, 0))

        while heap and len(res) < k:
            _, i, j = heappop(heap)

            res.append([nums1[i], nums2[j]])

            # i with j + 1
            if j + 1 < n2 and (i, j + 1) not in visited:
                visited.add((i, j + 1))
                heappush(heap, (nums1[i] + nums2[j + 1], i, j + 1))

            # i + 1 with j
            if i + 1 < n1 and (i + 1, j) not in visited:
                visited.add((i + 1, j))
                heappush(heap, (nums1[i + 1] + nums2[j], i + 1, j))

        return res
