package com.intern.Amazon;

import java.util.HashSet;

public class TwoSumUniquePair {
    public static int uniquePair(int[] nums, int target) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set1.add(nums[i]);
            if (set1.contains(target - nums[i])) {
                set2.add(nums[i]);
            }
        }
        return set2.size();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,45,46,46};
        int target = 47;
        System.out.println(uniquePair(nums, target));
    }
}
