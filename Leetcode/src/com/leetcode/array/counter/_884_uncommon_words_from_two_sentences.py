from collections import Counter
from typing import List


class Solution:
    def uncommonFromSentences(self, s1: str, s2: str) -> List[str]:
        c = Counter(f"{s1} {s2}".split())
        return [k for k in c if c[k] == 1]
