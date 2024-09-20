class Solution:
    def shortestPalindrome(self, s: str) -> str:
        rev = s[::-1]
        n = len(s)
        for i in range(n):
            if s[0 : n - i] == rev[i:]:
                return rev[0:i] + s
        return ""

