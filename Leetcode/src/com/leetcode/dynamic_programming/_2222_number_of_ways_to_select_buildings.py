'''
Date: 08/19/2022 11:37:19
LastEditTime: 08/19/2022 11:37:20
Description: DP
'''


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
