package com.leetcode.sortAlgo;

public class Insertion<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j - 1 >= 0 && less(nums[j], nums[j - 1]); j--) {
                exch(nums, j, j - 1);
            }
        }
    }

    // 这个的顺序是往前找，把最小的放到那里去。
    public static void main(String[] args) {
        Insertion<Integer> insertion = new Insertion<>();
        Integer[] nums = {5,4,3,2,1};
        insertion.sort(nums);
        for (int in : nums) {
            System.out.print(in + " ");
        }
    }
}
