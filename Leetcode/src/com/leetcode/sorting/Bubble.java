package com.leetcode.sorting;

public class Bubble<T extends Comparable<T>> extends Sort<T>{

    @Override
    public void sort(T[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) { // 相当于最多j+1可以等于nums[n - 1]
                if (less(nums[j + 1], nums[j])) {
                    exch(nums, j + 1, j);
                }
            }
        }
    }


    // normal version: https://www.geeksforgeeks.org/bubble-sort/
    // 每次交换，然后确认最后一个元素，然后倒数第二个。。。。
    public static void main(String[] args) {
        Bubble<Integer> bubble = new Bubble<>();
        Integer[] nums = {5,4,3,2,1};
        bubble.sort(nums);
        for (int in : nums) {
            System.out.print(in + " ");
        }
    }
}
