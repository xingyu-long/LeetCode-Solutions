class Solution:
    # time: O(O(log(target/startValue)))
    def brokenCalc(self, startValue: int, target: int) -> int:
        res = 0
        while target > startValue:
            if target % 2 == 0:
                target //= 2
            else:
                target += 1
            res += 1
        return res + startValue - target
