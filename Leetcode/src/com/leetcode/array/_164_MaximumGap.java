package com.leetcode.array;

import java.util.Arrays;

public class _164_MaximumGap {
    /**
     *  164. Maximum Gap
     *  When:2019/8/3
     *  Difficulty: Medium
     *  Solution:
     *      bucket sort
     *  需要在每个桶中找出局部最大值和最小值，而最大间距的两个数不会在同一个桶中，而是一个桶的最小值和另一个桶的最大值之间的间距
     */
    // time:O(n) space:O(n)
    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int len = nums.length;
        int max = nums[0];
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        // 设置桶的大小
        int gap = (int) Math.ceil((double) (max - min) / (len - 1));
        int[] bucketsMin = new int[len - 1]; // 设置有len - 1个桶
        int[] bucketsMax = new int[len - 1];
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        //找出每个桶的局部最大最小值
        for (int num : nums) {
            if (num == min || num == max) continue; // 肯定是局部的最优 所以不用计算
            int idx = (num - min) / gap;
            bucketsMin[idx] = Math.min(num, bucketsMin[idx]);
            bucketsMax[idx] = Math.max(num, bucketsMax[idx]);
        }

        int res = 0;
        int pre = min;
        for (int i = 0; i < len - 1; i++) {
            if (bucketsMin[i] == Integer.MAX_VALUE &&
            bucketsMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            res = Math.max(res, bucketsMin[i] - pre);
            // 这里后面的min减去前面的max，才是连续的！！！
            pre = bucketsMax[i];
        }
        res = Math.max(res, max - pre);// 更新最后一个
        return res;
    }
}
