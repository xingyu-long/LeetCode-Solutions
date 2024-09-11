from typing import List


class SparseVector:
    """
    solution:
    1) use two pointer to move around,
    2) also we can use binary search
        if vector1 is really sparse -> k elements
        and vector2 is not sparse -> n elements
        => k * log N
    """

    def __init__(self, nums: List[int]):
        self.idx_val = []
        for k, v in enumerate(nums):
            if v != 0:
                self.idx_val.append((k, v))

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: "SparseVector") -> int:
        res = 0
        i, j = 0, 0
        m, n = len(self.idx_val), len(vec.idx_val)
        while i < m and j < n:
            if self.idx_val[i][0] == vec.idx_val[j][0]:
                res += self.idx_val[i][1] * vec.idx_val[j][1]
                i, j = i + 1, j + 1
            elif self.idx_val[i][0] > vec.idx_val[j][0]:
                j += 1
            else:
                i += 1
        return res


# Your SparseVector object will be instantiated and called as such:
# v1 = SparseVector(nums1)
# v2 = SparseVector(nums2)
# ans = v1.dotProduct(v2)
