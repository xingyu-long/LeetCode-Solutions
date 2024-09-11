package com.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class _217_ContainsDuplicate {

    // time: O(n^2) space: O(n)
    public boolean containsDuplicate(int[] nums) {
        //solution1: 利用key-value的形式计数
        if (nums == null || nums.length < 1) {
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) { // o(n)
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            if (map.get(num) > 1) {
                return true;
            }
        }
        return false;
    }

    // time: O(nlogn) space: O(n)
    public boolean containsDuplicate2(int[] nums) {
        // 利用排序，然后前后两个找是否相同
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    // time: O(n) space: O(n)
    public boolean containsDuplicate3(int[] nums) {
//        利用set不能放入重复的特性
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }
}
