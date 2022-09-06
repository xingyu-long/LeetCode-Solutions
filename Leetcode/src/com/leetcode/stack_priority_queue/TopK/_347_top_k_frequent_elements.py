'''
Date: 09/05/2022 14:08:27
LastEditTime: 09/05/2022 14:14:31
Description: heapq, bucket sorting
'''

import heapq
from typing import List
from collections import Counter


class Solution:
    # time: O(nlogK)
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        count = Counter(nums)
        heap = []
        for key, freq in count.items():
            heapq.heappush(heap, (freq, key))
            if len(heap) > k:
                heapq.heappop(heap)
        return [x[1] for x in heap]

    # time:O(n)
    def topKFrequent2(self, nums: List[int], k: int) -> List[int]:
        count = Counter(nums)
        freq = [[] for _ in range(len(nums) + 1)]
        for key, val in count.items():
            freq[val].append(key)
        
        res = []
        for i in range(len(nums), -1, -1):
            for item in freq[i]:
                res.append(item)
                if len(res) == k:
                    return res
        return res