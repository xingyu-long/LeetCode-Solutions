class Solution:
    def compress(self, chars: List[str]) -> int:
        res = 0
        n = len(chars)
        i = 0
        while i < n:
            curr = chars[i]
            count = 0
            while i < n and chars[i] == curr:
                i = i + 1
                count += 1
            chars[res] = curr
            res += 1
            if count > 1:
                for ch in str(count):
                    chars[res] = ch
                    res += 1

        return res
