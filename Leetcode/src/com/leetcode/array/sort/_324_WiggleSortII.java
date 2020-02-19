package com.leetcode.array.sort;

import java.util.Arrays;

public class _324_WiggleSortII {

    /**
     * 324. Wiggle Sort II
     * When:2019/8/5
     * Difficulty: Medium
     */
    // 1. 排序，找中位数
    // 2. （从后面开始）大于中位数，从左到右看index，放置在奇数位
    // 3. （从前面开始）小于中位数，从右到左看index，放置在偶数位
    // 4. 中位数最后放
    // 有些不理解，以后再看 time:O(nlogN) space:O(n)
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int mid = (n - 1) / 2;
        int index = 0;
        int[] temp = new int[n];

        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < n) {
                temp[index + 1] = nums[n - 1 - i];
            }
            index += 2;
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }

    // 这个更加的易懂。分别从中间以及最后，倒着来。偶数位放中间开始的数，奇数位放最后开始的数。
    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        Arrays.sort(nums);
        int n = nums.length;
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            copy[i] = nums[i];
        }
        int end = n - 1;
        int mid = end / 2;
        int j = 0;
        while (mid >= 0 && end > mid) {
            nums[j] = j % 2 == 0 ? copy[mid--] : copy[end--];
            j++;
        }

        while (mid >= 0 && j < n) {
            nums[j++] = copy[mid--];
        }

        while (end > mid && j < n) {
            nums[j++] = copy[end--];
        }
    }
}