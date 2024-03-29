'''
Date: 08/09/2022 19:33:46
LastEditTime: 08/09/2022 19:37:59
Description: Greedy
'''
import operator
from typing import List


class Solution:
    """
    解题思路：
    首先是这个防御值只能用一次，我们需要求最少的开始生命值，那么我们应该
    把这个防御机会放在伤害值最高的情况上。所以首先会求出最大的伤害值，之后我们初始所需要的生命值
    是所有伤害的总和加上1，然后再最大伤害值的情况下用一次防御值抵御
    """
    def minimumHealth(self, damage: List[int], armor: int) -> int:
        max_idx, max_damage = max(enumerate(damage), key=operator.itemgetter(1))
        res = sum(damage) + 1
        for idx in range(len(damage)):
            if idx == max_idx:
                diff = max_damage - armor
                if diff > 0:
                    res -= armor
                else:
                    res -= max_damage
        return res

    # 与上面一样的思路只是简化了代码
    def minimumHealth2(self, damage: List[int], armor: int) -> int:
        return sum(damage) + 1 - min(max(damage), armor)