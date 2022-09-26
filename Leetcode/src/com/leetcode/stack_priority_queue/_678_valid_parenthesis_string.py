class Solution:
    def checkValidString(self, s: str) -> bool:
        # 计算左括号可能存在的情况
        # https://leetcode.com/problems/valid-parenthesis-string/discuss/543521/Java-Count-Open-Parenthesis-O(n)-time-O(1)-space-Picture-Explain
        left_min, left_max = 0, 0
        for c in s:
            if c == '(':
                left_min += 1
                left_max += 1
            elif c == ')':
                left_min -= 1
                left_max -= 1
            else:
                left_max += 1
                left_min -= 1
            if left_max < 0:
                return False
            left_min = max(left_min, 0)
        return left_min == 0
