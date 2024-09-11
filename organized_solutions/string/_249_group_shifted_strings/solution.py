from collections import defaultdict
from typing import List


class Solution:
    def groupStrings(self, strings: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strings:
            ch = s[0]
            encoding = ["1"]
            for i in range(1, len(s)):
                diff = ord(s[i]) - ord(s[i - 1])
                if diff < 0:
                    diff += 26
                encoding.append(str(diff))
            d["#".join(encoding)].append(s)
        return list(d.values())
