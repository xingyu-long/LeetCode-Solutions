package com.leetcode.string.sliding_window;

/**
 * @Date: 2019/7/22, 2019/8/29, 06/02/2020
 * @Description: Count
 **/
public class _485_MaxConsecutiveOnes {

    // time:O(n) space:O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 0; // 一旦不连续就从头开始
            }
        }
        return res;
    }

    // 可以想象成sliding window，表示0不能存在
    public int findMaxConsecutiveOnes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int start = 0, end = 0;
        int res = 0;
        while (end < nums.length) {
            if (nums[end] != 1) {
                count++;
            }
            while (count > 0) { // 只能有一种。
                if (nums[start] != 1) {
                    count--;
                }
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}
