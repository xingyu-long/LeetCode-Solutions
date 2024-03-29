# Definition for Employee.
from collections import deque
from typing import List


class Employee:
    def __init__(self, id: int, importance: int, subordinates: List[int]):
        self.id = id
        self.importance = importance
        self.subordinates = subordinates


class Solution:
    # time: O(n), space: O(n)
    def getImportance(self, employees: List['Employee'], id: int) -> int:
        id_to_obj = dict()
        for employee in employees:
            id_to_obj[employee.id] = employee
        obj = id_to_obj[id]
        queue = deque()
        res = 0
        queue.append(obj)
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                res += curr.importance
                for nxt in curr.subordinates:
                    queue.append(id_to_obj[nxt])
        return res
