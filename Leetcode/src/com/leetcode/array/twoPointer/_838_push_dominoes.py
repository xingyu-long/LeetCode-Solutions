class Solution:
    # time: O(N)
    # space: O(N)
    def pushDominoes(self, dominoes: str) -> str:
        """
        s = "LR.."

        -> "LLR..R"

        prev = 0 => L
        curr = 1 => L

        prev = 1 => L => res = ["L"]
        curr = 2 => R


        ...
        prev = 2 res = ["L",  "R",]
        curr = 5 => R
        span = 5 - 2 - 1 = 2

        res = ["L",  "R", "RR"] -> "LRRR"

        curr → stores the index of the next L or R
        prev → stores the index of the last L or R
        """
        s = "L" + dominoes + "R"
        res = []
        prev = 0
        for curr in range(1, len(s)):
            if s[curr] == ".":
                continue
            span = curr - prev - 1
            if prev > 0:
                res.append(s[prev])

            if s[prev] == s[curr]:
                res.append(s[prev] * span)
            elif s[prev] == "L" and s[curr] == "R":
                res.append("." * span)
            else:
                # s[prev] => R, s[curr] => "L"
                res.append("R" * (span // 2) + "." * (span % 2) + "L" * (span // 2))

            prev = curr

        return "".join(res)
