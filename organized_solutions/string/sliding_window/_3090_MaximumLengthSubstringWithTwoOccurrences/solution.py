"""
3090. Maximum Length Substring With Two Occurrences
---

Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.
---

Example 1:

Input: s = "bcbbbcba"
Output: 4
Explanation:
The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".

Example 2:
Input: s = "aaaa"
Output: 2
Explanation:
The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".
"""


class Solution:
    # time: O(n)
    # space: O(n), n = len(s)
    def maximumLengthSubstring(self, s: str) -> int:
        # sliding window
        start, end = 0, 0
        n = len(s)
        m = {}
        res = 0
        while end < n:
            m[s[end]] = m.get(s[end], 0) + 1
            while m[s[end]] > 2:
                m[s[start]] -= 1
                start += 1
            res = max(res, end - start + 1)
            end += 1
        return res
