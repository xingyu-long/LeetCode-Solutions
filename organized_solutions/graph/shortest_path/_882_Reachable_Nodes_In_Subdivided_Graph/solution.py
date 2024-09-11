'''
Date: 09/12/2021 18:25:12
LastEditTime: 09/12/2021 18:32:58
Description: Djistra's Algo
'''
from collections import defaultdict
from typing import List
import heapq


class Solution:
    def reachableNodes(self, edges: List[List[int]], maxMoves: int, n: int) -> int:
        d = defaultdict(dict)
        for u, v, w in edges:
            d[u][v] = d[v][u] = w
        pq = [(-maxMoves, 0)] # 利用负数来达到 max heap的效果
        HP = {}  # node-> hp
        while pq:
            hp, node = heapq.heappop(pq)
            if node not in HP:
                HP[node] = -hp # 这个还是保持之前的正数
                for next_node in d[node]:
                    next_hp = -hp - (d[node][next_node] + 1)
                    if next_node not in HP and next_hp >= 0:
                        heapq.heappush(pq, (-next_hp, next_node))
        res = len(HP)
        for u, v, w in edges:
            res += min(HP.get(u, 0) + HP.get(v, 0), w)
        return res


s = Solution()
s.reachableNodes([[0,1,10],[0,2,1],[1,2,2]], 6, 3)