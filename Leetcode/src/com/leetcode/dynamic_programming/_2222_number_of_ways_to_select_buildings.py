'''
Date: 08/19/2022 11:37:19
LastEditTime: 08/24/2022 15:23:02
Description: DP
'''
from collections import defaultdict


class Solution:
    # TLE
    def numberOfWays(self, s: str) -> int:
        d = dict()

        def dfs(s, idx, target):
            if idx >= len(s) and target != 0:
                return 0
            if target < 0:
                return 0
            if target == 0:
                return 1
            key = '-'.join([str(idx), str(target)])
            if idx in d:
                return d[key]
            ways = 0
            for next_idx in range(idx + 1, len(s)):
                if s[idx] == s[next_idx]:
                    continue
                ways += dfs(s, next_idx, target - 1)
            d[key] = ways
            return ways

        res = 0
        target = 3
        for x in range(len(s)):
            item = dfs(s, x, target - 1)
            res += item
        return res

    """
    Solution:
    因为是3个字符并且相邻的字符不能有重复的，所以对于结果只能来自于
    010和101的情况，对于这两个字符串，我们可以拆分成多个不同的情况，
    对于0结尾的字符串有0,10,010 所以我们需要求解 0的情况
    当当前的字符是0:
        对于10的情况则是来自于 dp['1']的时候
        对于010的情况则来自于 dp['01']的时候
    """
    # time: O(len(s))
    # space: O(6 -> 1)
    # follow up: what if we give k as another integer instead of 3:
    # https://leetcode.com/problems/number-of-ways-to-select-buildings/discuss/1907179/JavaPython-3-One-pass-T-O(n)-S-O(1)-codes-and-follow-up-w-brief-explanation-and-analysis.
    def numberOfWays2(self, s: str) -> int:
        """
       idx 
             0 0 1 1 0 1
         0   1 2 2 2 3 3
         1   0 0 1 2 2 3
         10  0 0 0 0 2 2
         01  0 0 2 4 4 7
         101 0 0 0 0 0 2
         010 0 0 0 0 4 4
        """
        dp = defaultdict(lambda: 0)
        for ch in s:
            if ch == '0':
                dp['0'] += 1
                dp['10'] += dp['1']
                dp['010'] += dp['01']
            elif ch == '1':
                dp['1'] += 1
                dp['01'] += dp['0']
                dp['101'] += dp['10']
            # print([dp[x] for x in ['0', '1', '10', '01', '101', '010']])

        return dp['010'] + dp['101']
