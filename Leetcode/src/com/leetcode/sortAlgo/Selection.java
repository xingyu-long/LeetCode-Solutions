package com.leetcode.sortAlgo;

public class Selection<T extends Comparable<T>> extends Sort<T> {
    // time:O(n^2) 并且比较次数不会根据输入改变
    @Override
    public void sort(T[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            exch(nums, i, min);
        }
    }

    public static void main(String[] args) {
        Selection<Integer> selection = new Selection<>();
        Integer[] nums = {5, 4, 3, 2, 1};
        selection.sort(nums);
        for (int in : nums) {
            System.out.print(in + " ");
        }
    }
}
