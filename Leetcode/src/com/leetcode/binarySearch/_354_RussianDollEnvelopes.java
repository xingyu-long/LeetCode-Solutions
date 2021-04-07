/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 03/30/2021 09:53:31
 * @Description: DP, Binary Search
 */
package com.leetcode.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class _354_RussianDollEnvelopes {

    // 将其转化为LIS问题
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]));
        // 这样后面如果同等w，有小的h也会被更新掉。
        // 同一个w只有最大h能用上 LIS problem
        int n = envelopes.length;
        int[] sorted = new int[n];
        int res = 0;
        sorted[res++] = envelopes[0][1];
        for (int i = 1; i < n; i++) {
            if (envelopes[i][1] > sorted[res - 1]) {
                sorted[res++] = envelopes[i][1];
            } else {
                int pos = find(sorted, 0, res - 1, envelopes[i][1]);
                sorted[pos] = envelopes[i][1];
            }
        }
        return res;
    }

    public int find(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target <= nums[left]) {
            return left;
        }
        if (target > nums[right]) {
            return right + 1;
        }
        return right;
    }
}
