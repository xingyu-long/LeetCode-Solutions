package com.leetcode.array.sub_array;

/**
 * @Date: 05/10/2020
 * @Description: Subarray
 **/
public class _1186_MaximumSubarraySumwithOneDeletion {

    // 这个用法很妙，参照的是53题一样的思路，只是多加了一个反向的，然后每个点删除。
    // time:O(n) space:O(n)
    public int maximumSum2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[] forward = new int[n]; // 以i结束的连续最大和 (0 ~ i)
        int[] backward = new int[n]; // 以i结束的连续最大和(i ~ n)
        int res = arr[0];
        forward[0] = arr[0];
        for (int i = 1; i < n; i++) {
            forward[i] = Math.max(forward[i - 1] + arr[i], arr[i]);
            res = Math.max(res, forward[i]); // 没有删除的情况下最大（从左到右的方向）
        }

        backward[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            backward[i] = Math.max(backward[i + 1] + arr[i], arr[i]);
            res = Math.max(res, forward[i]); // 没有删除的情况下（从右到左的方向）
        }
        // 头和尾 删除 不考虑吗？已经在前面计算过了
        for (int i = 1; i < n - 1; i++) {
            // 不包含一个元素的情况
            res = Math.max(res, forward[i - 1] + backward[i + 1]);
        }

        return res;
    }
}