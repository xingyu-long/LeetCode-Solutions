package com.leetcode.HashMap;

import java.util.HashMap;
import java.util.Map;

public class _697_Degree_of_an_Array {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        int degree = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!first.containsKey(nums[i])) {
                first.put(nums[i], i);
            }

            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > degree) {
                // 就直接更新
                res = i - first.get(nums[i]) + 1;
                degree = map.get(nums[i]);
            } else if (map.get(nums[i]) == degree) {
                // 比较同样的degree下面的长度最小
                res = Math.min(res, i - first.get(nums[i]) + 1);
            }
        }
        return res;
    }
}
