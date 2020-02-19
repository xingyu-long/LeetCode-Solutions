package com.leetcode.array.twoPointer;
import java.util.Arrays;

public class _26_RemoveDuplicateFromSortedArray {
    /**
     * 26. Remove Duplicate From Sorted Array
       When: 2019/2/11
       review 1: 2019/6/30
       review 2: 2019/8/20
     */

    // two pointer，待插入前面一个就是需要比较的数
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 1]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
