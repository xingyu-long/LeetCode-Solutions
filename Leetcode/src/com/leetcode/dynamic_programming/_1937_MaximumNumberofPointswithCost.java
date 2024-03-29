/*
 * @Date: 07/18/2022 21:27:00
 * @LastEditTime: 07/18/2022 21:28:19
 * @Description: DP
 */
package com.leetcode.dynamic_programming;

public class _1937_MaximumNumberofPointswithCost {

    class Solution {
        // https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344908/C%2B%2BJavaPython-3-DP-Explanation-with-pictures-O(MN)-Time-O(N)-Space
        // time: O(m * n)
        public long maxPoints(int[][] points) {
            if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
                return 0;
            }
            int m = points.length, n = points[0].length;
            long[] prev = new long[n];
            for (int j = 0; j < n; j++) {
                prev[j] = points[0][j];
            }
            for (int i = 1; i < m; i++) {
                long[] left = new long[n]; // maximum value from left
                long[] right = new long[n]; // maximum value from right
                long[] curr = new long[n];
                left[0] = prev[0];
                right[n - 1] = prev[n - 1];
                for (int j = 1; j < n; j++) {
                    // 可能是从左边位置来的或者是上一层的同一个位置
                    left[j] = Math.max(prev[j], left[j - 1] - 1);
                }
                for (int j = n - 2; j >= 0; j--) {
                    // 可能是从右边位置来的或者是上一层的同一个位置
                    right[j] = Math.max(prev[j], right[j + 1] - 1);
                }

                for (int j = 0; j < n; j++) {
                    curr[j] = points[i][j] + Math.max(left[j], right[j]);
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
}
