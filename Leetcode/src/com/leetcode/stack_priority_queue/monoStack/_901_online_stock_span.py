class StockSpanner:

    def __init__(self):
        self.stack = []  # (price, res)

    def next(self, price: int) -> int:
        # include itself
        res = 1
        while self.stack and price >= self.stack[-1][0]:
            res += self.stack.pop()[1]
        self.stack.append((price, res))
        return res
