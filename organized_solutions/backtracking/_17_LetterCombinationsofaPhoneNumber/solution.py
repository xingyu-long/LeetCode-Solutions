from typing import List


class Solution:
    # time: O(3^n * 4^m) 这里的n是在按键为3个字母的情况下 m是四个字母的情况下
    # space: O(3^n * 4^m)
    def letterCombinations(self, digits: str) -> List[str]:
        m = {
            "2": "abc",
            "3": "def",
            "4": "ghi",
            "5": "jkl",
            "6": "mno",
            "7": "pqrs",
            "8": "tuv",
            "9": "wxyz",
        }
        res = []

        def dfs(path, index):
            if index == len(digits):
                res.append(path)
                return

            chs = m[digits[index]]
            for ch in chs:
                dfs(path + ch, index + 1)

        if digits:
            dfs("", 0)
        return res
