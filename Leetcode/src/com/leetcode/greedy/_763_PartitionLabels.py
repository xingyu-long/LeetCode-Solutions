class Solution:
    def partitionLabels(self, S: str) -> List[int]:
        n = len(S)
        # Use HashMap to store the latest index for each char.
        right_most = {ch:index for index, ch in enumerate(S)}

        start, max_pos = 0, 0
        res = []
        for i in range(n):
            max_pos = max(max_pos, right_most[S[i]])
            if i == max_pos:
                res.append(i - start + 1)
                start = i + 1
        return res