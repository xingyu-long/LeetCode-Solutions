'''
Date: 03/14/2022 18:27:50
LastEditTime: 03/14/2022 18:27:50
Description: Parentheses
'''
from collections import deque


class Solution:
    def minRemoveToMakeValid(self, s: str) -> str:
        stack = deque()
        mismatch = set()
        for i in range(len(s)):
            if s[i] == '(':
                stack.append(i)
            elif s[i] == ')':
                if len(stack):
                    stack.pop()  # default pop from top.
                else:
                    mismatch.add(i)
        for item in list(stack):
            mismatch.add(item)
        res = []
        for i in range(len(s)):
            if i not in mismatch:
                res.append(s[i])
        return ''.join(res)
