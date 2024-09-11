'''
Date: 12/05/2021 20:00:16
LastEditTime: 12/05/2021 20:03:53
Description: Greedy
'''


from typing import List


class Solution:
    # Brute force: O(n^2)
    def minOperations(self, boxes: str) -> List[int]:
        res = [0] * len(boxes)
        for i in range(len(boxes)):
            curr_sum = 0
            for j in range(len(boxes)):
                if i == j:
                    continue
                if int(boxes[j]) == 1:
                    curr_sum += abs(i - j)
            res[i] = curr_sum
        return res

    # Time: O(n)
    def minOperations2(self, boxes: str) -> List[int]:
        res = [0] * len(boxes)
        left_count, left_cost = 0, 0
        right_count, right_cost = 0, 0
        n = len(boxes)
        for i in range(1, n):
            if boxes[i - 1] == '1':
                left_count += 1
            left_cost += left_count
            res[i] += left_cost
        for i in range(n - 2, -1, -1):
            if boxes[i + 1] == '1':
                right_count += 1
            right_cost += right_count
            res[i] += right_cost
        return res
