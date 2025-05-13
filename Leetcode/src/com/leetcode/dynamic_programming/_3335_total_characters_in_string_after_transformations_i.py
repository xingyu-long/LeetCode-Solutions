class Solution:
    # time: O(N * 26)
    # space: O(26) -> O(1)
    def lengthAfterTransformations(self, s: str, t: int) -> int:
        mod = 10**9 + 7
        counter = [0] * 26
        for ch in s:
            counter[ord(ch) - ord("a")] += 1
        for _ in range(t):
            temp = [0] * 26
            # a was from previous z = counter[25]
            temp[0] = counter[25]
            # b was from a and previous z = counter[0] + counter[25]
            temp[1] = (counter[25] + counter[0]) % mod
            for i in range(2, 26):
                # a -> b
                # b -> c
                # ...
                # x -> y (25)
                temp[i] = counter[i - 1]
            counter = temp
        return sum(counter) % mod
