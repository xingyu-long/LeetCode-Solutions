/*
 * @Date: 12/21/2020 10:56:42
 * @LastEditTime: 12/21/2020 11:25:34
 * @Description: Math, Greedy
 */
package com.leetcode.math;

import java.util.Arrays;

public class _910_SmallestRangeII {
    // 先排序，之后我们想找的是 A[i] + k, A[i + 1] - k的差值
    // https://massivealgorithms.blogspot.com/2018/11/leetcode-910-smallest-range-ii.html
    // 其实这个还是不太懂为什么需要以前以后的取。
    public int smallestRangeII(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        Arrays.sort(A);
        int n = A.length;
        int res = A[n - 1] - A[0];
        int left = A[0] + K, right = A[n - 1] - K;
        for (int i = 0; i < n - 1; i++) {
            int max = Math.max(right, A[i] + K);
            int min = Math.min(left, A[i + 1] - K);
            res = Math.min(res, max - min);
        }
        return res;
    }
}
