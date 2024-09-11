from collections import Counter


class Solution:
    # time: O(n)
    # space: O(n)
    def checkInclusion(self, s1: str, s2: str) -> bool:
        # slding window
        counter = Counter(s1)
        size = len(s1)
        n = len(s2)
        start, end = 0, 0
        while end < n:
            counter[s2[end]] -= 1
            if counter[s2[end]] >= 0:
                size -= 1
            while size == 0:
                # ^ this is the key and keep reduce the size
                if end - start + 1 == len(s1):
                    return True
                counter[s2[start]] += 1
                if counter[s2[start]] > 0:
                    size += 1
                start += 1
            end += 1
        return False
