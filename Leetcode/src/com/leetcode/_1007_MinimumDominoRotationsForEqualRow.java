package com.leetcode;

public class _1007_MinimumDominoRotationsForEqualRow {
    /**
     *
     * @param A
     * @param B
     * @return
     */
    public int minDominoRotations(int[] A, int[] B) {
        // 利用第一列元素，如果有肯定是其中一个！如果A[0]work就不用检查B[0]因为是相同的。
        int n = A.length;
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == A[0] || B[i] == A[0]); i++) {
            if (A[i] != A[0]) a++;// 表示B中有相同的 a需要交换
            if (B[i] != A[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }

        for (int i = 0, a = 0, b = 0; i < n && (B[i] == B[0] || A[i] == B[0]); i++) {
            if (A[i] != B[0]) a++;
            if (B[i] != B[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }
        return -1;
    }
}
