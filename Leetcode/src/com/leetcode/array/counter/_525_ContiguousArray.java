package com.leetcode.array.counter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 04/13/2020, 09/02/2020
 * @Description: prefix Sum, HashMap
 **/
public class _525_ContiguousArray {

    // time:O(n) space:O(n)
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : nums[i];
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }
}
