class Solution:
    # time: O(max(len(num1),len(num2)))
    # space: O(max(len))
    def addStrings(self, num1: str, num2: str) -> str:
        i, j = len(num1) - 1, len(num2) - 1
        carry = 0
        res = []
        while i >= 0 or j >= 0 or carry:
            num_i = num_j = 0
            if i >= 0:
                num_i = ord(num1[i]) - ord("0")
                i -= 1
            if j >= 0:
                num_j = ord(num2[j]) - ord("0")
                j -= 1

            carry += num_i + num_j
            res.append(str(carry % 10))
            carry //= 10

        return "".join(res)[::-1]
