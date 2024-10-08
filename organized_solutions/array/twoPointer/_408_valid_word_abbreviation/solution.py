class Solution:
    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        # two pointer
        i, j = 0, 0
        m, n = len(word), len(abbr)
        num = 0
        while i < m and j < n:
            if word[i] == abbr[j]:
                i += 1
                j += 1
            else:
                while j < n and abbr[j].isnumeric():
                    num = num * 10 + int(abbr[j])
                    # leading 0 => False
                    if num == 0:
                        return False
                    j += 1

                # word[i] != abbr[j] and there is no num to skip
                if num == 0:
                    return False
                else:
                    # we need to move i
                    i += num
                    num = 0

        return i == m and j == n
