'''
Date: 08/14/2022 16:15:33
LastEditTime: 08/14/2022 16:55:45
Description: Binary Search, count
'''
import bisect
from collections import defaultdict
from typing import List


class Solution:
    """
    解题思路：
    利用binary search去寻找大于start最接近的candle的index和
    小于等于end最接近的candle的index。这样的话，当前里面的所有元素则是
    candle_idxes[j] - candle_idxes[i] + 1 （包括candle和plate）
    当前里面的candle的数量则是 j - i + 1
    """

    # time: O(N + QlogN)
    # space: O(N + Q)
    def platesBetweenCandles(self, s: str, queries: List[List[int]]) -> List[int]:
        candle_idxes = [idx for idx, ch in enumerate(s) if ch == '|']
        res = []
        for start, end in queries:
            # find the leftmost index where start >= candle_idxes[index]
            i = bisect.bisect_left(candle_idxes, start)
            # find the rightmost index where candle_idxes[index] >= end
            j = bisect.bisect(candle_idxes, end) - 1
            count = (candle_idxes[j] - candle_idxes[i] +
                     1) - (j - i + 1) if i < j else 0
            res.append(count)
        return res

    """
                            0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20

                            *  *  *  |  *  *  |  *  *  *   *   *   |   *   *   |   |   *   *   |   *
    nearest right candle:   3  3  3  3  6  6  6  12 12 12  12 12  12  15  15  15   16  19  19  19  -
    nearest left candle:    -  -  -  3  3  3  6  6  6  6   6  6   12  12  12  15  16  16  16   19  19
    candle count:           0  0  0  1  1  1  2  2  2  2   2  2    3   3   3   4   5   5   5   6   6
    """

    # time: O(Q + N)
    # space: O(Q + N)
    def platesBetweenCandles2(self, s: str, queries: List[List[int]]) -> List[int]:
        prev_candle = defaultdict(lambda: -1)
        next_candle = defaultdict(lambda: len(s))
        prev_idx = -1
        for idx, ch in enumerate(s):
            if ch == '|':
                prev_idx = idx
            prev_candle[idx] = prev_idx

        next_idx = len(queries)
        for idx, ch in reversed(list(enumerate(s))):
            if ch == '|':
                next_idx = idx
            next_candle[idx] = next_idx

        count = 0
        candle_count = [0] * len(s)
        for idx, ch in enumerate(s):
            if ch == '|':
                count += 1
            candle_count[idx] = count

        res = []
        for start, end in queries:
            left_candle = next_candle[start]
            right_candle = prev_candle[end]
            diff = 0
            if left_candle == -1 or right_candle == len(s):
                res.append(0)
            else:
                diff = right_candle - left_candle
                count = 0
                if diff > 1:
                    count = right_candle - left_candle + 1 - \
                        (candle_count[right_candle] -
                         candle_count[left_candle] + 1)
                res.append(count)
        return res
