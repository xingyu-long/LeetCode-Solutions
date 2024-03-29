package com.leetcode.array.sub_array;

import java.util.HashMap;

/**
 * @Date: 07/20/2020, 09/03/2020
 * @Description: HashMap, prefix sum.
 **/
public class _325_MaximumSizeSubarraySumEqualsk {
    // hashMap的做法 首先求累计和，然后看nums[i] - k是否在map中存在
    // 存在的话表明，i - 这个值就是长度 （需要好好理解）
    // 累计和有 == k的情况，那就是 i + 1 所以用map(0, -1) 就方便计算
    // 这里是最长，所以没问题，每次一旦发现没有插入的就记录，记录最早出现的位置。
    public static int maxSubArrayLen2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                // 累计和存在等于k的情况
                res = Math.max(res, i - map.get(sum - k));
            }
            // 表示这个sum没有出现的话，我就加入
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }
}