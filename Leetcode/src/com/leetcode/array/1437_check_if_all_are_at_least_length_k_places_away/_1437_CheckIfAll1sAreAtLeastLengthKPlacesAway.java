/*
 * @Date: 01/25/2021 12:46:04
 * @LastEditTime: 01/25/2021 12:46:41
 * @Description: Array
 */
package com.leetcode.array;

public class _1437_CheckIfAll1sAreAtLeastLengthKPlacesAway {
    public boolean kLengthApart(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int index = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (index != -1 && i - index - 1 < k)
                    return false;
                index = i;
            }
        }
        return true;
    }
}
