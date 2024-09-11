class Solution:
    # time: O(n)
    # space: O(1)
    def minAddToMakeValid(self, s: str) -> int:
        left, right = 0, 0
        for ch in s:
            if ch == "(":
                right += 1
            else:
                if right > 0:
                    right -= 1
                else:
                    left += 1
        return left + right
