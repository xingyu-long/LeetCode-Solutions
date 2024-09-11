from collections import defaultdict, deque
from typing import List


class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        indegree = [0] * numCourses
        d = defaultdict(set)
        res = []
        queue = deque()

        for curr, prev in prerequisites:
            d[prev].add(curr)
            indegree[curr] += 1
        for i in range(numCourses):
            if indegree[i] == 0:
                queue.append(i)

        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                res.append(curr)
                for nxt in d[curr]:
                    indegree[nxt] -= 1
                    if indegree[nxt] == 0:
                        queue.append(nxt)

        return res if len(res) == numCourses else []
