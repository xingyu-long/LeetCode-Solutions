from typing import List


class Solution:
    # time: O(4^N)
    # space: O(n)
    def addOperators(self, num: str, target: int) -> List[str]:

        res = []

        # 2+3*4, prev=3
        def dfs(idx, total, path, prev):
            if idx == len(num) and target == total:
                res.append(path)
                return

            for i in range(idx, len(num)):
                # "012"
                # to avoid 105 -> 1*5 (invalid)
                if i != idx and num[idx] == "0":
                    break
                curr = int(num[idx : i + 1])

                if idx == 0:
                    # there is no sign for first digit
                    dfs(i + 1, total + curr, str(curr), curr)
                else:
                    dfs(i + 1, total + curr, path + "+" + str(curr), curr)
                    dfs(i + 1, total - curr, path + "-" + str(curr), -curr)
                    # example: 1 - 2 * 2 + 3
                    # total = -1, prev = -2
                    # curr = 2
                    dfs(
                        i + 1,
                        (total - prev) + prev * curr,
                        path + "*" + str(curr),
                        prev * curr,
                    )

        dfs(0, 0, "", 0)
        return res
