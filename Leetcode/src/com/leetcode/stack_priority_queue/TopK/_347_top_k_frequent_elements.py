"""
347. Top K Frequent Elements
---

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
---

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:

Input: nums = [1], k = 1
Output: [1]
---

topics: heapq, bucket sorting
"""

import heapq
from typing import List
from collections import Counter


class Solution:
    # heapq
    # time: O(nlogK)
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        count = Counter(nums)
        heap = []
        for key, freq in count.items():
            heapq.heappush(heap, (freq, key))
            if len(heap) > k:
                heapq.heappop(heap)
        return [x[1] for x in heap]


class Solution2:
    # bucket sorting
    # time: O(n)
    # space: O(n)
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        if not nums:
            return []
        counter = Counter(nums)
        max_freq = max(counter.values()) + 1
        buckets = [[] for _ in range(max_freq)]
        for item, freq in counter.items():
            buckets[freq].append(item)
        res = []
        for i in range(max_freq)[::-1]:
            for item in buckets[i]:
                if len(res) < k:
                    res.append(item)
                else:
                    break
        return res
