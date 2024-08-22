class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False

        num = x
        reverse_num = 0
        while num > 0:
            reverse_num = reverse_num * 10 + num % 10
            num //= 10
        return reverse_num == x
