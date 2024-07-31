class Solution:
    # time: O(n)
    # prefix and suffix array to solve this
    def minimumDeletions(self, s: str) -> int:
        # 没有要求a的数量和b的数量相同，不要想多了
        n = len(s)
        # how many b that shouldn't exist before idx
        b_arr = [0] * n
        # how many a that shouldn't exist after idx
        a_arr = [0] * n

        b_count = 0
        for i in range(n):
            b_arr[i] = b_count
            if s[i] == "b":
                b_count += 1

        a_count = 0
        for i in range(n)[::-1]:
            a_arr[i] = a_count
            if s[i] == "a":
                a_count += 1

        res = n
        for i in range(n):
            res = min(res, a_arr[i] + b_arr[i])
        return res


class Solution:
    # remove all "ba" pairs
    def minimumDeletions(self, s: str) -> int:
        res = 0
        stack = []
        for ch in s:
            if stack and stack[-1] == "b" and ch == "a":
                stack.pop()
                res += 1
            else:
                stack.append(ch)
        return res
