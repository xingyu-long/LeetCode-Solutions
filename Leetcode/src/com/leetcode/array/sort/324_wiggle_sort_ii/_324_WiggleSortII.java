package com.leetcode.array.sort;

import java.util.Arrays;

public class _324_WiggleSortII {

    // 切为两半，然后从后开始构造！
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] copy = new int[n];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        int left = (n - 1) / 2, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = copy[left--];
            } else {
                nums[i] = copy[right--];
            }
        }
    }
}