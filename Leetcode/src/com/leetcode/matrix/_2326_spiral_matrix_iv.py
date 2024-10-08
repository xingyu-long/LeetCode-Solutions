from typing import List, Optional
from leetcode.common.py_utils import ListNode


class Solution:
    def spiralMatrix(self, m: int, n: int, head: Optional[ListNode]) -> List[List[int]]:
        res = [[-1] * n for _ in range(m)]
        row_start = col_start = 0
        row_end, col_end = m - 1, n - 1
        curr = head
        while row_start <= row_end and col_start <= col_end and curr:
            for j in range(col_start, col_end + 1):
                if not curr:
                    break
                res[row_start][j] = curr.val
                curr = curr.next
            row_start += 1

            for i in range(row_start, row_end + 1):
                if not curr:
                    break
                res[i][col_end] = curr.val
                curr = curr.next
            col_end -= 1

            for j in range(col_start, col_end + 1)[::-1]:
                if not curr:
                    break
                res[row_end][j] = curr.val
                curr = curr.next
            row_end -= 1

            for i in range(row_start, row_end + 1)[::-1]:
                if not curr:
                    break
                res[i][col_start] = curr.val
                curr = curr.next
            col_start += 1

        return res
