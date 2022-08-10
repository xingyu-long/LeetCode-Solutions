/*
 * @Date: 01/14/2021 11:15:13
 * @LastEditTime: 01/14/2021 11:17:59
 * @Description: Prefix Sum.
 */
package com.leetcode.array.sub_array;

import java.util.HashMap;
import java.util.Map;

public class _1658_MinimumOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        // 1423的follow up感觉
        // 反着求sum - x的区域最大
        int target = -x;
        for (int num : nums) {
            target += num;
        }
        if (target == 0)
            return nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, maxLen = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target)) {
                maxLen = Math.max(maxLen, i - map.get(sum - target));
            }
            map.put(sum, i);
        }
        return maxLen == Integer.MIN_VALUE ? -1 : n - maxLen;
    }
}
