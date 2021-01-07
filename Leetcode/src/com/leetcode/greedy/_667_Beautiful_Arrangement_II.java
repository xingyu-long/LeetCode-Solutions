/*
 * @Date: 10/10/2020 21:11:09
 * @LastEditTime: 01/03/2021 09:59:24
 * @Description: Greedy
 */
package com.leetcode.greedy;

public class _667_Beautiful_Arrangement_II {
    // time: O(N)
    // space: O(N) for output.
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        int left = 1, right = n;
        for (int i = 0; i < n; i++) {
            if (k > 1) {
                // need more difference;
                res[i] = (k % 2 != 0 ? left++ : right--);
                k--;
            } else {
                res[i] = left++;
            }
        }
        return res;
    }
}
