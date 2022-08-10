/*
 * @Date: 05/13/2020 14:30:25
 * @LastEditTime: 06/04/2022 19:09:26
 * @Description: HashMap, Prefix Sum
 */
package com.leetcode.array.sub_array;

import java.util.HashMap;
import java.util.Map;


public class _523_ContinuousSubarraySum {
    // time: O(n), space: O(n)
    // (sum2 - sum1) % k = 0 -> sum2 % k = sum1 % k
    // 我们用sum % k来判断，如果再次出现就说明其某一段子数组的和符合要求
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
                // 代表着从map.get(sum) + 1, i的和。
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
