class Solution:
    def compressedString(self, word: str) -> str:
        i = 0
        n = len(word)
        res = []
        while i < n:
            curr = word[i]
            count = 0
            while i < n and curr == word[i] and count < 9:
                i += 1
                count += 1
            res.append(str(count))
            res.append(curr)
        return "".join(res)
