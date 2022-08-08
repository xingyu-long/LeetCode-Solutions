/*
 * @Date: 01/06/2021 10:42:29
 * @LastEditTime: 01/06/2021 10:43:29
 * @Description: String
 */
package com.leetcode.string;

public class _955_DeleteColumnsToMakeSortedII {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int m = A.length, n = A[0].length();
        boolean[] sorted = new boolean[m];
        int i = 0, j = 0;
        int res = 0;
        for (j = 0; j < n; j++) {
            for (i = 0; i < m - 1; i++) {
                if (!sorted[i] && A[i].charAt(j) > A[i + 1].charAt(j)) {
                    res++;
                    break;
                }
            }
            if (i < m - 1)
                continue;
            for (i = 0; i < m - 1; i++) {
                sorted[i] |= (A[i].charAt(j) < A[i + 1].charAt(j));
            }
        }
        return res;
    }
}
