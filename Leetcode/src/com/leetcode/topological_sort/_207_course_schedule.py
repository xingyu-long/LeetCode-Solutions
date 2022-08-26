'''
Date: 08/26/2022 14:22:58
LastEditTime: 08/26/2022 14:24:12
Description: Topological Sort
'''


from collections import defaultdict, deque
from typing import List


class Solution:
    # time: O(N + E)
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        indegree = [0] * numCourses
        d = defaultdict(set)
        for nxt, prev in prerequisites:
            d[prev].add(nxt)
            indegree[nxt] += 1

        queue = deque()
        for i in range(numCourses):
            if indegree[i] == 0:
                queue.append(i)

        k = 0
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                k += 1
                if curr in d:
                    for nxt in d[curr]:
                        indegree[nxt] -= 1
                        if indegree[nxt] == 0:
                            queue.append(nxt)
        return k == numCourses
