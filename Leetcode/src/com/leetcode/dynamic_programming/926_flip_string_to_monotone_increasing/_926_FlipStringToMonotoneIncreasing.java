/*
 * @Date: 08/10/2021 09:51:44
 * @LastEditTime: 08/10/2021 16:12:55
 * @Description: Prefix, Suffix, DP
 */
package com.leetcode.dynamic_programming;

public class _926_FlipStringToMonotoneIncreasing {
    public int minFlipsMonoIncr(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] convertToOne = new int[n + 1]; // [j] -> [j, n] minimal changes to one;
        int[] convertToZero = new int[n + 1]; // [i] -> [0, i) minimal changes to zero;

        for (int i = 1, j = n - 1; j >= 0; i++, j--) {
            convertToZero[i] = convertToZero[i - 1] + (s.charAt(i - 1) == '0' ? 0 : 1);
            convertToOne[j] = convertToOne[j + 1] + (s.charAt(j) == '1' ? 0 : 1);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            res = Math.min(res, convertToZero[i] + convertToOne[i]);
        }
        return res;
    }
}
