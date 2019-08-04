package com.leetcode.array.counter;

import java.util.HashMap;

public class _325_MaximumSizeSubarraySumEqualsk {
    /**
     *  325. Maximum Size Subarray Sum Equals k
     *  When:2019/8/4
     *  Difficulty: Medium
     * @param nums
     * @param k
     * @return
     */

    // brute force time: O(n^2) space:O(1)
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // 针对于 nums = [3] k = 3
            int sum = nums[i];
            if (sum == k) {
                res = Math.max(res, 1);
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    // hashMap的做法 首先求累计和，然后看nums[i] - k是否在map中存在
    // 存在的话表明，i - 这个值就是长度 （需要好好理解）
    // 累计和有 == k的情况，那就是 i + 1 所以用map(0, -1) 就方便计算
    public int maxSubArrayLen2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        // 计算累计和
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - k)) {
                // 累计和存在等于k的情况
                res = Math.max(res, i - map.get(nums[i] - k));
            }
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}