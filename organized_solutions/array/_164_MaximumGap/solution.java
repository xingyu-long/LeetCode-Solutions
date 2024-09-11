package com.leetcode.array;

import java.util.Arrays;

public class _164_MaximumGap {

    // 需要在每个桶中找出局部最大值和最小值，而最大间距的两个数不会在同一个桶中，而是一个桶的最小值和另一个桶的最大值之间的间距
    // time:O(n) space:O(n)
    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = nums.length;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // 设置桶间隔，这样保证最大的gap不会在桶内，而是在两者之间
        int gap = (max - min) / n + 1;
        int bucketNum = (max - min) / gap + 1;
        int[] bucketMin = new int[bucketNum];
        int[] bucketMax = new int[bucketNum];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // 将数字放入bucket，并且保留该bucket的最大最小值。
        for (int num : nums) {
            int index = (num - min) / gap;
            bucketMin[index] = Math.min(bucketMin[index], num);
            bucketMax[index] = Math.max(bucketMax[index], num);
        }

        int res = 0;
        int pre = 0;
        for (int i = 1; i < bucketNum; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE ||
                    bucketMax[i] == Integer.MIN_VALUE) continue;
            // 表示空桶。
            res = Math.max(res, bucketMin[i] - bucketMax[pre]);
            pre = i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 100};
        System.out.println(maximumGap(nums));
    }
}
