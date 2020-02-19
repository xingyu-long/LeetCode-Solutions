package com.leetcode.prefixSum;

public class _370_RangeAddition {
    // O(kn)
    public int[] getModifiedArray(int length, int[][] updates) {
        if (length == 0) return new int[]{};
        int[] res = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int add = update[2];
            // add
            for (int i = start; i <= end; i++) {
                res[i] += add;
            }
        }
        return res;
    }

    // 其实也是利用累计和的情况。感觉用的很巧妙。
    // https://leetcode.com/problems/range-addition/discuss/84225/Detailed-explanation-if-you-don't-understand-especially-%22put-negative-inc-at-endIndex%2B1%22
    public int[] getModifiedArray2(int length, int[][] updates) {
        if (length == 0) return new int[]{};
        int[] res = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            res[start] += val;
            // 对于end+1做 -val的操作。相当于抵消前面的加入的操作，表示在index+1以及之后不会受到前面的val的影响才对
            if (end + 1 < length) {
                res[end + 1] -= val;
            }
        }
        for (int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
