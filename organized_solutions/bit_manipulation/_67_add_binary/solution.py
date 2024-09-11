class Solution:
    def addBinary(self, a: str, b: str) -> str:
        res = []
        i = len(a) - 1
        j = len(b) - 1
        carry = 0
        while i >= 0 or j >= 0 or carry != 0:
            if i >= 0:
                num_a = ord(a[i]) - ord("0")
                carry += num_a
                i -= 1
            if j >= 0:
                num_b = ord(b[j]) - ord("0")
                carry += num_b
                j -= 1

            res.append(str(carry % 2))
            carry //= 2

        return "".join(reversed(res))
