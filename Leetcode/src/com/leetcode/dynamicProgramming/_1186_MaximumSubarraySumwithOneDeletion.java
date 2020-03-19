/*
 * @Date: 2020-03-18 21:03:20
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-18 23:56:26
 */
package com.leetcode.dynamicProgramming;

public class _1186_MaximumSubarraySumwithOneDeletion {

    // time: O(n) space:O(n)
    // 其数组表示到当前点 最大的连续和。（所以不删除的数组依然会选择大的连续和情况而并非所有的和）
    public int maximumSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[] delete = new int[n];
        int[] notDelete = new int[n]; // 表示当前为是否delete
        delete[0] = 0;
        notDelete[0] = arr[0];
        int max = arr[0]; // 至少选一个，所以我选这个
        for (int i = 1; i < n; i++) {
            // 之前错误的写法。用来对比
            // delete[i] = Math.max(delete[i], notDelete[i - 1]);
            // notDelete[i] = Math.max(delete[i - 1], notDelete[i - 1]) + arr[i];
            delete[i] = Math.max(delete[i - 1] + arr[i], notDelete[i - 1]);
            notDelete[i] = Math.max(notDelete[i - 1] + arr[i], arr[i]);
            max = Math.max(Math.max(max, delete[i]), notDelete[i]);
        }
        return max;
    } 

    // 这个用法很妙，参照的是53题一样的思路，只是多加了一个反向的，然后每个点删除。
    // time:O(n) space:O(n)
    public int maximumSum2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[] forward = new int[n]; // 以i结束的连续最大和 (0 ~ i)
        int[] backward = new int[n]; // 以i结束的连续最大和(i ~ n)
        int max = arr[0];
        forward[0] = arr[0];
        for (int i = 1; i < n; i++) {
            forward[i] = Math.max(forward[i - 1] + arr[i], arr[i]);
            max = Math.max(max, forward[i]);
        }
        
        backward[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            backward[i] = Math.max(backward[i + 1] + arr[i], arr[i]);
        }
        // 头和尾 删除 不考虑吗？
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, forward[i - 1] + backward[i + 1]);
        }
        
        return max;
    }
}