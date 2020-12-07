'''
Date: 12/07/2020 09:42:28
LastEditTime: 12/07/2020 09:43:42
Description: Simulation, Matrix
'''


class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        res = [[0 for _ in range(n)] for _ in range(n)]
        row_begin, row_end = 0, n - 1
        col_begin, col_end = 0, n - 1
        number = 1
        while row_begin <= row_end and col_begin <= col_end:
            for j in range(col_begin, col_end + 1):
                res[row_begin][j] = number
                number += 1
            row_begin += 1
            if row_begin > row_end:
                break

            for i in range(row_begin, row_end + 1):
                res[i][col_end] = number
                number += 1
            col_end -= 1
            if col_end < col_begin:
                break

            for j in range(col_end, col_begin - 1, -1):
                res[row_end][j] = number
                number += 1
            row_end -= 1
            if row_end < row_begin:
                break

            for i in range(row_end, row_begin - 1, -1):
                res[i][col_begin] = number
                number += 1
            col_begin += 1
            if col_begin > col_end:
                break
        return res
