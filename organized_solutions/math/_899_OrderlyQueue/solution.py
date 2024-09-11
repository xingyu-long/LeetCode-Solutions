'''
Date: 09/05/2021 11:51:37
LastEditTime: 09/05/2021 11:56:27
Description: Math
'''


# 分两种情况讨论：（1）如果k=1，那就只能逐个移动并且比较
# （2）k > 1，那就一定定找到有序的情况
# time: O(n^2)
class Solution:
    def orderlyQueue(self, s: str, k: int) -> str:
        if k > 1:
            return "".join(sorted(s))
        return min(s[i:] + s[:i] for i in range(len(s)))
