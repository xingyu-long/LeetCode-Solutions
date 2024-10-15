class Solution:
    # time: O(NlogN)
    def maxKelements(self, nums: List[int], k: int) -> int:
        if not nums:
            return 0
        res = 0
        heap = []
        for num in nums:
            heappush(heap, -num)

        for _ in range(k):
            item = -heappop(heap)
            res += item
            heappush(heap, -(math.ceil(item / 3)))

        return res
