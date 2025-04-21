from collections import defaultdict
from typing import List


class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        """
        如果某个兔子回答的数字是x，那么说明森林里共有x+1个相同颜色的兔子，我们最
        多允许x+1个兔子同时回答x个，一旦超过了x+1个兔子，那么就得再增加了x+1个新兔子了。
        """
        # 某种颜色兔子的总个数和在数组中还允许出现的个数之间的映射
        m = defaultdict(int)
        res = 0
        for num in answers:
            if m[num + 1] == 0:
                res += num + 1
                m[num + 1] = num
            else:
                m[num + 1] -= 1
        return res
