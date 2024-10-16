class Solution:
    # also Greedy
    def longestDiverseString(self, a: int, b: int, c: int) -> str:
        heap = []
        for freq, s in [(a, "a"), (b, "b"), (c, "c")]:
            if freq != 0:
                heappush(heap, (-freq, s))

        res = []
        while heap:
            freq, s = heappop(heap)
            if len(res) > 1 and res[-2] == res[-1] == s:
                # same and we need to get another char
                if not heap:
                    break
                freq, s = heapreplace(heap, (freq, s))
            res.append(s)
            # freq is negative
            if freq + 1 != 0:
                heappush(heap, (freq + 1, s))

        return "".join(res)
