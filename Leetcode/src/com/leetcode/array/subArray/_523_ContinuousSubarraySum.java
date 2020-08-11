package com.leetcode.array.subArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 05/13/2020
 * @Description: HashMap
 **/
public class _523_ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        // if (k == 0) return true;
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}
