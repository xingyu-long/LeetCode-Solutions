package com.leetcode.string.slidingWindow;

public class _1004_MaxConsecutiveOnesIII {

    /**
     *  1004. Max Consecutive Ones III
     *  When:2019/7/21
     *  Difficulty: Medium
     *  solution:
     *  利用0出现的次数来维护sliding window
     *  https://leetcode.com/problems/max-consecutive-ones-iii/discuss/248287/java-sliding-windows-with-comments-in-line
     * @param A
     * @param K
     * @return
     */
    // time:O(n) space:O(1)
    public int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        int res = 0;
        int zeroCount = 0;
        int start = 0;
        for (int end = 0; end < A.length; end++) {
            if (A[end] == 0) {
                zeroCount++;
            }
            while (zeroCount > K) {
                if (A[start] == 0) {
                    zeroCount--;
                }
                start++;//移动窗口
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
