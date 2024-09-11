package com.leetcode.matrix;

public class _1292_MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold {
    // time: O(N^2) 
    // 利用range sum优化计算过程。
    public int maxSideLength(int[][] mat, int threshold) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
            return 0;
        }
        int m = mat.length, n = mat[0].length;

        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] + 
                    prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        
        int res = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = res + 1; k <= Math.min(m - i, n - j); k++) {
                    if (sumRegion(prefix, i, j, i + k - 1, j + k - 1) <= threshold) {
                        res = k;
                    } else {
                        break;
                    }
                }
            }
        }
        return res;
    }
    
    public int sumRegion(int[][] prefix, int row1, int col1, int row2, int col2) {
        int r1 = row1 + 1, c1 = col1 + 1, r2 = row2 + 1, c2 = col2 + 1;
        return prefix[r2][c2] - prefix[r1 - 1][c2] - prefix[r2][c1 - 1] + prefix[r1 - 1][c1 - 1];
    }
}