from collections import defaultdict
from typing import List


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            count = [0] * 26
            for c in s:
                count[ord(c) - ord("a")] += 1
            d[tuple(count)].append(s)
        return d.values()


# if we have char other than lowercase English letters
class Solution2:
    # time:(nmlogm): n->len(strs), m-> max(str)
    def groupAnagrams(self, strs: List[str]) ->  List[List[str]]:
        m = defaultdict(list)
        for s in strs:
            sorted_str = "".join(sorted(s))
            m[sorted_str].append(s)
        return list(m.values())
