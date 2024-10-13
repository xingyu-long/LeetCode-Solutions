class Solution:
    # time: O(NlogN + N)
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        # sliding window
        # how many groups it has covered
        counter = defaultdict(int)
        k = len(nums)
        arr = []
        for i in range(k):
            for num in nums[i]:
                arr.append((num, i))
        arr.sort(key=lambda x: x[0])
        res = []
        start = end = 0
        diff = float("inf")
        n = len(arr)
        while end < n:
            counter[arr[end][1]] += 1
            while len(counter) == k:
                # we have k groups for this range
                if arr[end][0] - arr[start][0] < diff:
                    diff = arr[end][0] - arr[start][0]
                    res = [arr[start][0], arr[end][0]]
                counter[arr[start][1]] -= 1
                if counter[arr[start][1]] == 0:
                    del counter[arr[start][1]]
                start += 1
            end += 1

        return res
