class Solution:
    def canIWin(self, maxChoosableInteger: int, desiredTotal: int) -> bool:
        total = maxChoosableInteger * (maxChoosableInteger + 1) / 2
        if desiredTotal <= maxChoosableInteger:
            return True
        if total < desiredTotal:
            return False

        memo = {}
        chosen = [False] * (maxChoosableInteger + 1)

        def canWin(memo, chosen, rest):
            if rest <= 0:
                return False
            key = str(chosen)
            if key in memo:
                return memo[key]

            for i in range(1, maxChoosableInteger + 1):
                if chosen[i]:
                    continue

                chosen[i] = True
                # the peer cannot win and I win!
                if not canWin(memo, chosen, rest - i):
                    chosen[i] = False
                    memo[key] = True
                    return True
                chosen[i] = False

            memo[key] = False
            return False

        return canWin(memo, chosen, desiredTotal)
