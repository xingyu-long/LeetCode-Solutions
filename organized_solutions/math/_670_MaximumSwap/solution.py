class Solution:
    # time: O(n)
    # space: O(n)
    def maximumSwap(self, num: int) -> int:
        # greedy
        arr = [int(x) for x in str(num)]
        max_idx = len(arr) - 1
        xi = yi = 0
        for i in range(len(arr))[::-1]:
            if arr[i] > arr[max_idx]:
                max_idx = i
            elif arr[i] < arr[max_idx]:
                xi = i
                yi = max_idx
        arr[xi], arr[yi] = arr[yi], arr[xi]
        return int("".join([str(x) for x in arr]))
