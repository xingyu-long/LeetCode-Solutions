from collections import Counter

"""
424. Longest Repeating Character Replacement
---

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.
---

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
"""

class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        start, end = 0, 0
        n = len(s)
        c = Counter()
        res, max_repeat = 0, 0
        while end < n:
            c[s[end]] += 1
            max_repeat = max(max_repeat, c[s[end]])
            # max_repeat makes window smaller, so we won't see the case which we
            # have the smaller window however, line 15 equation is greater than k.
            while (end - start + 1) - max_repeat > k:
                c[s[start]] -= 1
                start += 1
                # or use following line to get the occurence of
                # majority element in such window.
                # max_repeat = max(c.values)
            res = max(res, end - start + 1)
            end += 1
        return res
