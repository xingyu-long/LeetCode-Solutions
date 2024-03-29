package com.leetcode.bfs_and_dfs;

import java.util.ArrayList;
import java.util.List;


public class _52_NQueensII {
    /**
     * 52. N-Queens II
     * When: 2019/06/05
     * review1:11/1/2019
     * solution: 利用三个数组来进行判断
     * @param n
     * @return
     */
    int res = 0;
    // time:O(n * n) space:O(n)
    public int totalNQueens(int n) {
        // 利用三个boolean array
        boolean[] cols = new boolean[n];
        boolean[] diagonalLeft = new boolean[2 * n]; // row - col 相同 '\'
        boolean[] diagonalRight = new boolean[2 * n]; // row +  col 相同 '/'
        dfs(n, cols, diagonalLeft, diagonalRight, 0);
        return res;
    }
    // https://leetcode.wang/leetCode-52-N-QueensII.html 看里面的图
    public void dfs(int n, boolean[] cols, boolean[] diagonalLeft, boolean[] diagonalRight, int row) {
        if (row == n) {
            res++;
            return;
        } else {
            for (int col = 0; col < n; col++) { // 同一列这种情况永远不会出现！因为直接去了下一行里面
                int d1 = row - col + n;//防止是负数
                int d2 = row + col;
                if (cols[col] || diagonalLeft[d1] || diagonalRight[d2]) continue;
                cols[col] = true;
                diagonalLeft[d1] = true;
                diagonalRight[d2] = true;
                dfs(n, cols, diagonalLeft, diagonalRight, row + 1);
                cols[col] = false;
                diagonalLeft[d1] = false;
                diagonalRight[d2] = false;
            }
        }
    }
}
