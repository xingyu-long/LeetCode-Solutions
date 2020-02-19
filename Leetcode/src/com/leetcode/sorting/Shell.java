package com.leetcode.sorting;

public class Shell<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int n = nums.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1; // 1, 4, 13, 40, ...3x + 1 sequence

        while (h >= 1) {
            for (int  i = h; i < n; i++) {
                for (int j = i; j - h >= 0 && less(nums[j], nums[j - h]); j -= h) {
                    exch(nums, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        Shell<Integer> shell = new Shell<>();
        Integer[] nums = {5,4,3,2,1};
        shell.sort(nums);
        for (int in : nums) {
            System.out.print(in + " ");
        }
    }
}
