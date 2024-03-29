'''
Date: 08/24/2022 16:54:22
LastEditTime: 08/24/2022 16:59:44
Description: BFS, shortest path
'''
from collections import deque
from typing import List


class Solution:
    """
    Solution:
    这个是个典型的BFS的题目，但是需要理解的是如果去寻找
    下一个点，这个时候需要去看当前坐标下是否有正值（代表着
    可以直接跳跃到下一个点）没有的话就去尝试接下来的6个值
    依次加入到queue里面去
    """
    def snakesAndLadders(self, board: List[List[int]]) -> int:

        def get_position(curr, size):
            i, j = (curr - 1) // size, (curr - 1) % size
            if i % 2 == 1:
                j = size - 1 - j
            i = size - 1 - i
            return i, j

        size, steps = len(board), 0
        queue = deque()
        queue.append(1)
        visited = [False] * (size * size + 1)
        visited[1] = True
        while queue:
            level_size = len(queue)
            for _ in range(level_size):
                curr = queue.popleft()
                if curr == size * size:
                    return steps
                for next_curr in range(curr + 1, min(curr + 6, size ** 2) + 1):
                    i, j = get_position(next_curr, size)
                    nxt = next_curr if (board[i][j] == -1) else board[i][j]
                    if visited[nxt]:
                        continue
                    visited[nxt] = True
                    queue.append(nxt)
            steps += 1

        return -1
