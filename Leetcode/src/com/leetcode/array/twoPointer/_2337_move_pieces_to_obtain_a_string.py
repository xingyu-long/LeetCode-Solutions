class Solution:
    def canChange(self, start: str, target: str) -> bool:
        # two pointer
        i, j = 0, 0
        n = len(start)
        while i < n or j < n:
            while i < n and start[i] == "_":
                i += 1
            while j < n and target[j] == "_":
                j += 1

            if (
                i == n
                or j == n
                or start[i] != target[j]
                or (start[i] == "L" and i < j)
                or (start[i] == "R" and i > j)
            ):
                break

            i, j = i + 1, j + 1

        return i == j == n
