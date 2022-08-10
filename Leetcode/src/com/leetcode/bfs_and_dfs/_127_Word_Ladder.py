'''
Date: 08/09/2022 15:46:35
LastEditTime: 08/09/2022 15:47:40
Description: BFS
'''
import string
from collections import deque
from typing import List


class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        queue = deque()
        s, visited = set(wordList), set()
        if endWord not in s:
            return 0
        s.discard(beginWord)
        queue.append(beginWord)
        steps = 1
        while len(queue) != 0:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                for i in range(len(curr)):
                    chs = list(curr)
                    for tmp in string.ascii_lowercase:
                        chs[i] = tmp
                        new_str = ''.join(chs)
                        if new_str in s and new_str not in visited:
                            visited.add(new_str)
                            queue.append(new_str)
                            if new_str == endWord:
                                steps += 1
                                return steps
            steps += 1
        return 0
