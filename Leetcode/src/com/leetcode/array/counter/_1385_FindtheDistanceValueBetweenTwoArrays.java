/*
 * @Date: 2020-03-23 21:10:33
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-23 21:11:29
 */
package com.leetcode.array.counter;

public class _1385_FindtheDistanceValueBetweenTwoArrays {
    // time:O(mn) space:O(1);
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        if (arr1 == null || arr2 == null) return 0;
        int n = arr1.length, m = arr2.length;
        int countMax = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int diff = Math.abs(arr1[i] - arr2[j]);
                if (diff <= d) {
                    countMax++;
                    break;
                }
            }
        }
        return n - countMax;
    }
}