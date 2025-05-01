from typing import List

from sortedcontainers import SortedList


class Solution:
    # time: O(N log^2 N)
    def maxTaskAssign(
        self, tasks: List[int], workers: List[int], pills: int, strength: int
    ) -> int:
        def can_assign(k: int) -> bool:
            # Check if we can assign k tasks to workers.

            # the first k of tasks
            _tasks = SortedList(tasks[:k])
            # the last k of workers
            _workers = workers[-k:]
            remain_pills = pills
            print(f"_tasks={_tasks}, _workers={_workers}")

            for worker in _workers:
                task = _tasks[0]
                if worker >= task:
                    # worker can finish this without pill
                    # remove the task and move on
                    _tasks.pop(0)
                elif worker + strength >= task and remain_pills:
                    # worker need the pill and we need to find the next
                    # task we can finish (as close to worker + strength as possible)
                    remove_task_idx = _tasks.bisect_right(worker + strength)
                    _tasks.pop(remove_task_idx - 1)
                    remain_pills -= 1
                else:
                    return False
            return True

        # finish the small task first?
        workers.sort()
        tasks.sort()
        # this is not the idx, this should be the range
        # of how many tasks we can try to finish.
        left, right = 1, min(len(tasks), len(workers))

        while left + 1 < right:
            mid = left + (right - left) // 2
            if can_assign(mid):
                left = mid
            else:
                right = mid
        if can_assign(right):
            return right
        elif can_assign(left):
            return left
        else:
            return 0
