package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _801_MinimumSwapsToMakeSequencesIncreasing {
    // 801. Minimum Swaps To Make Sequences Increasing
    public int minSwap(int[] A, int[] B) {
        // 其实这个和house robber有点相似，每一次有两个选择。
        // 可能前面交换了之后就可以让后面保持顺序了
        int n = A.length;
        int[] keep = new int[n];
        Arrays.fill(keep, Integer.MAX_VALUE);
        int[] swap = new int[n];
        Arrays.fill(swap, Integer.MAX_VALUE);
        keep[0] = 0;
        swap[0] = 1;

        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                keep[i] = keep[i - 1];
                swap[i] = swap[i - 1] + 1;
            }
            if (B[i] > A[i - 1] && A[i] > B[i - 1]) {
                swap[i] = Math.min(swap[i], keep[i - 1] + 1);
                keep[i] = Math.min(keep[i], swap[i - 1]);
            }
        }
        return Math.min(swap[n - 1], keep[n - 1]);
    }
}
