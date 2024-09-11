package com.leetcode.array.sub_array;

/**
 * @Date: 08/30/2020, 09/02/2020
 * @Description: DP
 **/
public class _1567_MaximumLengthofSubarrayWithPositiveProduct {
    // 可以作为152的follow up
    public int getMaxLen(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] pos = new int[n]; // 以当前i结尾，积为正数的最长个数。
        int[] neg = new int[n]; // 以当前i结尾，积为负数的最长个数。
        pos[0] = nums[0] > 0 ? 1 : 0;
        neg[0] = nums[0] < 0 ? 1 : 0;
        int res = pos[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                // 当前为正数：（1）从上一个pos继续获得
                // （2）上一个neg不为空的话，也可以从上面增加（因为正负得负）
                pos[i] = pos[i - 1] + 1;
                neg[i] = neg[i - 1] > 0 ? neg[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                // 当前为负数：（1）如果上一个neg存在，那么在这个时候我们可以构成正的情况。
                // （2）直接从上一个pos继续获得（正负得负）
                pos[i] = neg[i - 1] > 0 ? neg[i - 1] + 1 : 0;
                neg[i] = pos[i - 1] + 1;
            }
            // 需要跳过等于0的情况，不做考虑
            res = Math.max(res, pos[i]);
        }
        return res;
    }
}
