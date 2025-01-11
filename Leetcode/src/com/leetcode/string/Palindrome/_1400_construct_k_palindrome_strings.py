class Solution:
    # time: O(N)
    # space: O(1)
    def canConstruct(self, s: str, k: int) -> bool:
        counter = Counter(s)
        odd = sum([freq % 2 for freq in counter.values()])
        # 如果k小于奇数个数，那就不能构成
        # 如果k大于总长度，也不行
        # 其他的情况都可以
        # example
        # aabbc, k=1 | aacbb
        # aabbc, k=2 | aca, bb
        # aabbc, k=3 | a,a, bcb
        # aabbc, k=4 | a, a, c ,bb
        # aabbc, k=5 | a, a, c, b, b
        return odd <= k <= len(s)
