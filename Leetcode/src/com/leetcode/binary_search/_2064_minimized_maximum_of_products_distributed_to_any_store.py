class Solution:
    # time: O(log(max quantity))
    def minimizedMaximum(self, n: int, quantities: List[int]) -> int:

        def can_place(amount: int, arr: List[int]) -> bool:
            count = 0
            i = 0
            for q in arr:
                # we always need one more store to store the left (if there is 0.xxx from the calculation)
                count += math.ceil(q / amount)
            return count <= n

        left, right = 1, max(quantities)
        while left + 1 < right:
            mid = left + (right - left) // 2
            if can_place(mid, quantities):
                right = mid
            else:
                left = mid
        if can_place(left, quantities):
            return left
        if can_place(right, quantities):
            return right
        return -1
