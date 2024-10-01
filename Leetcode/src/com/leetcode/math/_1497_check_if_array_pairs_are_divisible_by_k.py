class Solution:
    def canArrange(self, arr: List[int], k: int) -> bool:
        freqs = [0] * k
        for num in arr:
            idx = num % k
            if idx < 0:
                idx += k
            freqs[idx] += 1

        # iterate the half.
        for i in range(k // 2 + 1):
            if i == 0:
                if freqs[i] % 2 != 0:
                    return False
            elif freqs[i] != freqs[k - i]:
                return False

        return True
