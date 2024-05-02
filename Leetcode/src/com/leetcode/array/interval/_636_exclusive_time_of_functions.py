from typing import List


class Solution:
    # time: O(len(logs))
    # time: O(len(logs) // 2)
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        # stack
        stack = []
        res = [0] * n
        prev = 0
        for log in logs:
            i, status, time = log.split(":")
            if status == "start":
                if stack:
                    # s1 -> s2 -> e2 -> e1
                    # this is for time between s1 -> s2
                    res[stack[-1]] += int(time) - prev
                stack.append(int(i))
                prev = int(time)
            elif status == "end":
                idx = stack.pop()
                res[idx] += int(time) - prev + 1
                # because each task is squentially connected
                prev = int(time) + 1
        return res
