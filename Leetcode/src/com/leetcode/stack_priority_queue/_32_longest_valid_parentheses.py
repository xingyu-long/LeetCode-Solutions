class Solution:
    def longestValidParentheses(self, s: str) -> int:
        left_most = -1
        res = 0
        stack = []
        # NOTE: key is to find the left_most or stack[-1] which represents the beginning of valid parentheses
        """
        example 1:
        ( ( ) )
        0 1 2 3
        left_most = -1 => 3 - (-1) = 4

        example 2:
        ( ( ( ) )
        0 1 2 3 4
        |
        |----> stack[-1] = 0 => 4 - (0) = 4

        example 3:
        ) ( ) ( )
        0 1 2 3 4
        |
        |---> left_most = 0 => 4 - (0) = 4
        """
        for i in range(len(s)):
            if s[i] == "(":
                stack.append(i)
            else:
                if stack:
                    # pop the index of '('
                    stack.pop()
                    if stack:
                        res = max(res, i - stack[-1])
                    else:
                        res = max(res, i - left_most)
                else:
                    left_most = i

        return res
