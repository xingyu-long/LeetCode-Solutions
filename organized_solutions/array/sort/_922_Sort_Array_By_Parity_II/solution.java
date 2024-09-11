package com.leetcode.array.sort;

public class _922_Sort_Array_By_Parity_II {
    public int[] sortArrayByParityII(int[] A) {
        if (A == null || A.length == 0) {
            return new int[] {};
        }
        // 两两换位置
        int i = 0, j = 1, n = A.length;
        while (i < n && j < n) {
            if (A[i] % 2 == 0) {
                i += 2;
            } else if (A[j] % 2 == 1) {
                j += 2;
            } else { // 直到走到两个位置都不对的时候。
                exch(A, i, j);
            }
        }
        return A;
    }

    private void exch(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
