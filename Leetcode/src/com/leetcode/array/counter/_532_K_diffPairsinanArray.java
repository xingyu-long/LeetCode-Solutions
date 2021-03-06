package com.leetcode.array.counter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Date: 07/19/2020
 * @Description: HashMap
 **/
public class _532_K_diffPairsinanArray {

    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        // put the smaller one at the beginning
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - k)) {
                set.add((nums[i] - k) + " * " + nums[i]);
            }
            if (map.containsKey(nums[i] + k)) {
                set.add(nums[i] + " * " + (nums[i] + k));
            }
            map.put(nums[i], i);
        }
        return set.size();
    }

    public int findPairs2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int res = 0;
        for (int key : map.keySet()) {
            if (k == 0) {
                if (map.get(key) > 1) {
                    res++;
                }
            } else {
                if (map.containsKey(key + k)) {
                    res++;
                }
            }
        }
        return res;
    }
}
