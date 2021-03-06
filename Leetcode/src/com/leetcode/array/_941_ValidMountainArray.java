package com.leetcode.array;

/*
 * @Date: 12/10/2020 10:21:21
 * @LastEditTime: 12/10/2020 10:22:07
 * @Description: 前后扫描一遍找peak
 */

public class _941_ValidMountainArray {
    public boolean validMountainArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int n = arr.length;
        if (n < 3)
            return false;
        int l2r = 0;
        int r2l = n - 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                l2r = i;
            } else {
                break;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                r2l = i;
            } else {
                break;
            }
        }
        return l2r == r2l && l2r != 0 && r2l != n - 1;
    }
}