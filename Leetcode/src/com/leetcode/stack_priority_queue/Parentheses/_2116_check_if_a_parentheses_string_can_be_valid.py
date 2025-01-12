class Solution:
    def canBeValid(self, s: str, locked: str) -> bool:
        # check `(`
        n = len(s)
        if n % 2 == 1:
            return False

        balance = 0
        for i in range(n):
            if locked[i] == "0" or s[i] == "(":
                balance += 1
            else:
                balance -= 1
            if balance < 0:
                return False

        # scan from right to left again to solve ["))((", "0011"]
        # check `)`
        balance = 0
        for i in range(n)[::-1]:
            if locked[i] == "0" or s[i] == ")":
                balance += 1
            else:
                balance -= 1

            if balance < 0:
                return False

        return True
