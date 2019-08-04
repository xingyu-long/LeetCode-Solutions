package com.leetcode.array.sort;

import java.util.Arrays;

public class _280_WiggleSort {

    /**
     *  280. Wiggle Sort
     *  When:2019/8/4
     *  Difficulty: Medium
     * @param nums
     */
    // time:O(nlogn) space:O(1)
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;

        Arrays.sort(nums);

        for (int i = 2; i < nums.length; i += 2) {
            exch(nums, i, i - 1);
        }
    }

    //这道题还有一种O(n)的解法，根据题目要求的nums[0] <= nums[1] >= nums[2] <= nums[3]....，我们可以总结出如下规律：
    //当i为奇数时，nums[i] >= nums[i - 1]
    //当i为偶数时，nums[i] <= nums[i - 1]
    // time: O(n) space:O(1)
    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                exch(nums, i, i - 1);
            }
        }
    }
    public void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
