/*
 * @Date: 01/07/2021 11:51:41
 * @LastEditTime: 01/07/2021 11:52:12
 * @Description: Interval
 */
package com.leetcode.array.interval;

import java.util.Arrays;

public class _646_MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0] == null || pairs[0].length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        // end soon and place more intervals;
        int end = pairs[0][1];
        int res = 1, n = pairs.length;
        for (int i = 1; i < n; i++) {
            if (pairs[i][0] > end) {
                res++;
                end = pairs[i][1];
            } else {
                end = Math.min(end, pairs[i][1]);
            }
        }
        return res;
    }
}
