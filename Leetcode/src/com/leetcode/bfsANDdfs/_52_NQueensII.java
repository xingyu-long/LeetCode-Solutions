package com.leetcode.bfsANDdfs;

import java.util.ArrayList;
import java.util.List;


public class _52_NQueensII {
    int res = 0;

    /**
     * 52. N-Queens II
     * When: 2019/06/05
     *
     * solution: 跟之前的n queens解法一样，只是这里只是需要计数就行。
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        helper(0, new ArrayList<Integer>(), n);
        return res;
    }

    public void helper(int row, List<Integer> colPlacements, int n) {
        if (row == n) {
            res++;
        } else {
            for (int col = 0; col < n; col++) {
                colPlacements.add(col);
                if (isValid(colPlacements)) {
                    helper(row + 1, colPlacements, n);
                }
                colPlacements.remove(colPlacements.size() - 1);
            }
        }
    }

    public boolean isValid(List<Integer> colPlacements) {
        int currentRow = colPlacements.size() - 1;
        for (int i = 0; i < currentRow; i++) {
            int diff = Math.abs(colPlacements.get(i) - colPlacements.get(currentRow));
            if (diff == 0 || diff == Math.abs(i - currentRow)) {
                return false;
            }
        }
        return true;
    }
}
