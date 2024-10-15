class Solution:
    def minimumSteps(self, s: str) -> int:
        arr = list(s)
        print(arr)
        n = len(arr)
        left, right = 0, n - 1
        res = 0
        while left < right:
            while left < right and arr[left] == "0":
                left += 1

            while left < right and arr[right] == "1":
                right -= 1

            res += right - left

            arr[left], arr[right] = arr[right], arr[left]

            left, right = left + 1, right - 1

        return res


class Solution2:
    def minimumSteps(self, s: str) -> int:
        res = 0
        count = 0
        for ch in s:
            if ch == "1":
                count += 1
            else:
                # move steps (# of count) from current "0"
                res += count

        return res
