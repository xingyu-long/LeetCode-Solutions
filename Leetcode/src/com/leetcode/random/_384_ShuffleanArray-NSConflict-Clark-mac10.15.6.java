package com.leetcode.random;

import java.util.Random;

public class _384_ShuffleanArray {

    private int[] nums;
    private Random rmd;

    public _384_ShuffleanArray(int[] nums) {
        this.nums = nums;
        rmd = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    //time:O(n)
    // Fisher–Yates shuffle Algorithm
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) return null;
        int[] clone = nums.clone();
        int n = clone.length;
        for (int i = n - 1; i > 0; i--) {
            int j = rmd.nextInt(i + 1);
            exch(clone, i, j);
        }
        return clone;
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
