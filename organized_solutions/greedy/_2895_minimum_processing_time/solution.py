from typing import List


class Solution:
    # 对于每一个processor（这个也是按照开始时间排序），优先处理时间比较长的
    # time: O(nlogn + mlogm + n + m)
    def minProcessingTime(self, processorTime: List[int], tasks: List[int]) -> int:
        # greedy
        processorTime.sort()
        tasks.sort(reverse=True)
        i = 0
        res = 0
        for processor in processorTime:
            for _ in range(4):
                res = max(res, processor + tasks[i])
                i += 1
        return res
