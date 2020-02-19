package com.leetcode.array.twoPointer;

public class _977_SquaresofaSortedArray {
    public int[] sortedSquares(int[] A) {
        // time:O(n) space:O(n)
        // 因为是填充，需要先比较。
        if (A == null || A.length == 0) return new int[]{};
        int[] res = new int[A.length];
        int k = res.length - 1;
        int left = 0;
        int right = k;
        while (left <= right) { // 取到最后
            if (A[left] * A[left] < A[right] * A[right]) {
                res[k--] = A[right] * A[right];
                right--;
            } else  {
                res[k--] = A[left] * A[left];
                left++;
            }
        }
        return res;
    }
}
