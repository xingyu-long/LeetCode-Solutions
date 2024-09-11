from math import inf


class Solution:
    def bestClosingTime(self, customers: str) -> int:
        n = len(customers)
        prefix, postfix = [0] * (n + 1), [0] * (n + 1)
        # prefix: before closing_time, how many N
        # postfix: after closing_time, how many Y
        for i in range(1, n + 1):
            prefix[i] += prefix[i - 1]
            if customers[i - 1] == "N":
                prefix[i] += 1

        for i in range(n)[::-1]:
            postfix[i] += postfix[i + 1]
            if customers[i] == "Y":
                postfix[i] += 1

        min_penalty, res = inf, 0
        for i in range(n + 1):
            penalty = prefix[i] + postfix[i]
            if penalty < min_penalty:
                min_penalty = penalty
                res = i
        return res
