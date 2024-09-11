from collections import deque
from typing import List


class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        word_set = set(wordList)
        if endWord not in word_set:
            return 0
        visited = set()
        queue = deque()
        res = 1
        queue.append(beginWord)
        visited.add(beginWord)
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                for i in range(len(curr)):
                    temp = list(curr)
                    for j in range(26):
                        temp[i] = chr(ord("a") + j)
                        temp_str = "".join(temp)
                        if temp_str in visited:
                            continue
                        if temp_str == endWord:
                            return res + 1
                        if temp_str in word_set:
                            queue.append(temp_str)
                            visited.add(temp_str)
            res += 1

        return 0
