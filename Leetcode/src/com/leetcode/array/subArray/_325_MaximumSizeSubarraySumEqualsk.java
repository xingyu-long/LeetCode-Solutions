package com.leetcode.array.subArray;

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
    // hashMap的做法 首先求累计和，然后看nums[i] - k是否在map中存在
    // 存在的话表明，i - 这个值就是长度 （需要好好理解）
    // 累计和有 == k的情况，那就是 i + 1 所以用map(0, -1) 就方便计算
    // 这里是最长，所以没问题，每次一旦发现没有插入的就记录，记录最早出现的位置。
    public static int maxSubArrayLen2(int[] nums, int k) {
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

    public static void main(String[] args) {
        int[] nums = {1, 0, -1};
        int k = -1;
        maxSubArrayLen2(nums, k);
    }
}