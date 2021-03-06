package com.leetcode.greedy;

import java.util.Arrays;

/**
 * Created by longxingyu on 2019/2/12.
 */
public class _80_RemoveDuplicateFromSortedArrayII {
    /**
     * 80. Remove Duplicates from Sorted Array II
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 2; // 因为至少有一个
        for (int fast = 2; fast < nums.length; fast++) {
            // 通过检查slow-2来确定是否可以插入。
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}