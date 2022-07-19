/*
 * @Date: 07/18/2022 21:27:00
 * @LastEditTime: 07/18/2022 21:28:19
 * @Description: You need to fill out
 */
package com.leetcode.dynamicProgramming;

public class _1937_MaximumNumberofPointswithCost {
    //https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344908/C%2B%2BJavaPython-3-DP-Explanation-with-pictures-O(MN)-Time-O(N)-Space
    // time: O(m * n)
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] prev = new long[n];
        // fill prev with the first row
        for (int j = 0; j < n; j++) {
            prev[j] = points[0][j];
        }
        for (int i = 0; i < m - 1; i++) {
            long[] left = new long[n], right = new long[n];
            long[] curr = new long[n];
            left[0] = prev[0];
            right[n - 1] = prev[n - 1];
            for (int j = 1; j < n; j++) {
                // 可能是从左边位置来的或者是上一层的同一个位置
                left[j] = Math.max(left[j - 1] - 1, prev[j]);
            }
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, prev[j]);
            }
            for (int j = 0; j < n; j++) {
                // 可能是从右边位置来的或者是上一层的同一个位置
                curr[j] = points[i + 1][j] + Math.max(left[j], right[j]);
            }
            prev = curr;
        }
        long res = 0;
        for (int j = 0; j < n; j++) {
            res = Math.max(res, prev[j]);
        }
        return res;
    }
}
