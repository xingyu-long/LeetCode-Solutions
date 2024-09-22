class Solution:
    # https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/solutions/92242/concise-easy-to-understand-java-5ms-solution-with-explaination/
    # time: O((logN)^2)
    def findKthNumber(self, n: int, k: int) -> int:

        def cal_steps(curr):
            steps = 0
            n1, n2 = curr, curr + 1
            while n1 <= n:
                steps += min(n + 1, n2) - n1
                n1 *= 10
                n2 *= 10
            return steps

        curr = 1
        k -= 1
        while k > 0:
            # from curr to curr + 1
            steps = cal_steps(curr)
            if steps <= k:
                curr += 1
                k -= steps
            else:
                # move to next level
                curr *= 10
                k -= 1
        return curr
