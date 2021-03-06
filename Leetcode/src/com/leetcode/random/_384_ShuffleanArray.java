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
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) return null;
        int[] clone = nums.clone();
        for (int i = 0; i < clone.length; i++) {
            // 直接从random[0, n-1]的元素。
            int random = i + rmd.nextInt(clone.length - i);
            exch(clone, i, random);
        }
        return clone;
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
