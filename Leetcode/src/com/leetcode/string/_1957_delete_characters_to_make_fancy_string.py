class Solution:
    def makeFancyString(self, s: str) -> str:
        # stack: [[ch_1, count_1], [ch_2, count_2] ...]
        stack = []
        for ch in s:
            if stack and stack[-1][0] == ch:
                if stack[-1][1] < 2:
                    stack[-1][1] += 1
            else:
                stack.append([ch, 1])

        res = []
        for ch, count in stack:
            res.append(ch * count)
        return "".join(res)


class Solution2:
    def makeFancyString(self, s: str) -> str:
        res = []
        prev = None
        count = 0
        for ch in s:
            if prev == ch:
                count += 1
            else:
                count = 1
            prev = ch
            if count < 3:
                res.append(ch)

        return "".join(res)
